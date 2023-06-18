package com.codelab.nfc

interface NfcRepository {
    fun setNfcPlainText(plainText: String)

    fun getNfcPlainText(): String
}