package com.codelab.nfcreader

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.nfc.tech.MifareUltralight
import android.os.Parcelable
import android.util.Log
import com.codelab.nfc.NfcRepository
import com.codelab.nfcreader.parser.NdefMessageParser
import com.codelab.nfcreader.parser.ParsedNdefRecord
import com.codelab.nfcreader.parser.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveNfcPayloadUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val nfcRepository: NfcRepository
) {
    suspend operator fun invoke(intent: Intent) = withContext(defaultDispatcher) {
        val action = intent.action
        var plainText = ""

        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            Log.i("Alex", "NDEF")
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                 plainText = makeUpPlainText(rawMessages.map { it as NdefMessage })
            }
        }

        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action) {
            Log.i("Alex", "TAG or TECH")
            val empty = ByteArray(0)
            val id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
            val tag = intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG) as Tag?
            val payload = dumpTagData(tag).toByteArray()
            val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload)
            val msg = NdefMessage(arrayOf(record))

            plainText = makeUpPlainText(listOf(msg))
        }

        if (plainText.isNotEmpty()) nfcRepository.setNfcPlainText(plainText)
    }

    private fun makeUpPlainText(msgs: List<NdefMessage?>?): String {
        if (msgs.isNullOrEmpty()) return ""

        val builder = StringBuilder()
        val records: List<ParsedNdefRecord> = NdefMessageParser.parse(msgs[0])
        val size = records.size

        for (i in 0 until size) {
            val record: ParsedNdefRecord = records[i]
            val str: String = record.str()
            builder.append(str).append("\n")
        }

        return builder.toString()
    }

    private fun dumpTagData(tag: Tag?): String {
        if (tag == null) return ""

        val sb = StringBuilder()
        val id = tag.id
        sb.append("ID (hex): ").append(Utils.toHex(id)).append('\n')
        sb.append("ID (reversed hex): ").append(Utils.toReversedHex(id)).append('\n')
        sb.append("ID (dec): ").append(Utils.toDec(id)).append('\n')
        sb.append("ID (reversed dec): ").append(Utils.toReversedDec(id)).append('\n')
        val prefix = "android.nfc.tech."
        sb.append("Technologies: ")
        for (tech in tag.techList) {
            sb.append(tech.substring(prefix.length))
            sb.append(", ")
        }
        sb.delete(sb.length - 2, sb.length)
        for (tech in tag.techList) {
            if (tech == MifareClassic::class.java.name) {
                sb.append('\n')
                var type = "Unknown"
                try {
                    val mifareTag = MifareClassic.get(tag)
                    when (mifareTag.type) {
                        MifareClassic.TYPE_CLASSIC -> type = "Classic"
                        MifareClassic.TYPE_PLUS -> type = "Plus"
                        MifareClassic.TYPE_PRO -> type = "Pro"
                    }
                    sb.append("Mifare Classic type: ")
                    sb.append(type)
                    sb.append('\n')
                    sb.append("Mifare size: ")
                    sb.append(mifareTag.size.toString() + " bytes")
                    sb.append('\n')
                    sb.append("Mifare sectors: ")
                    sb.append(mifareTag.sectorCount)
                    sb.append('\n')
                    sb.append("Mifare blocks: ")
                    sb.append(mifareTag.blockCount)
                } catch (e: Exception) {
                    sb.append("Mifare classic error: " + e.message)
                }
            }
            if (tech == MifareUltralight::class.java.name) {
                sb.append('\n')
                val mifareUlTag = MifareUltralight.get(tag)
                var type = "Unknown"
                when (mifareUlTag.type) {
                    MifareUltralight.TYPE_ULTRALIGHT -> type = "Ultralight"
                    MifareUltralight.TYPE_ULTRALIGHT_C -> type = "Ultralight C"
                }
                sb.append("Mifare Ultralight type: ")
                sb.append(type)
            }
        }
        return sb.toString()
    }
}