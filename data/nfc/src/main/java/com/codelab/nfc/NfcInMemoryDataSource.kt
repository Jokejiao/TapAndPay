package com.codelab.nfc

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NfcInMemoryDataSource @Inject constructor() {

    private var nfcPlainText = ""

    fun setNfcPlainText(plainText: String) {
        if (plainText.isNotEmpty()) nfcPlainText = plainText
    }

    fun getNfcPlainText() = nfcPlainText
}