package com.codelab.nfcreader

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.util.Log
import javax.inject.Inject


class StartNfcReaderUseCase @Inject constructor(
) {
    operator fun invoke(activity: Activity): NfcStatus {

        val nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
            ?: return NfcStatus.NFC_UNSUPPORTED

        // Start the reader anyway. It can read the card once the NFC is turned on
        val pendingIntent = PendingIntent.getActivity(
            activity, 0,
            Intent(activity, activity.javaClass)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE
        )

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, null, null)

        if (!nfcAdapter.isEnabled) {
            return NfcStatus.NFC_DISABLED
        }

        Log.i("Alex", "NFC_OK")
        return NfcStatus.NFC_OK
    }
}

enum class NfcStatus {
    NFC_UNSUPPORTED,
    NFC_DISABLED,
    NFC_OK
}