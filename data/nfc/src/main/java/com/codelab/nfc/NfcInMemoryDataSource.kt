package com.codelab.nfc

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NfcInMemoryDataSource @Inject constructor() {

    private var nfcPlainText = ""

    fun setNfcPlainText(plainText: String) {
        if (plainText.isNotEmpty()) nfcPlainText = plainText
        Log.i("Alex", "setNfc Thread${Thread.currentThread()}")
        Log.i("Alex", "painText=$nfcPlainText")
    }

    fun getNfcPlainText() = nfcPlainText
}