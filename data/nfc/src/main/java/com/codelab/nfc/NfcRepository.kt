package com.codelab.nfc

interface NfcRepository {
    suspend fun setNfcPlainText(plainText: String)

    suspend fun getNfcPlainText(): String
}