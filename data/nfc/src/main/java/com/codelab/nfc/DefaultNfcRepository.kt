package com.codelab.nfc

import javax.inject.Inject


class DefaultNfcRepository @Inject constructor(
  private val nfcInMemoryDataSource: NfcInMemoryDataSource
) : NfcRepository {

    override fun setNfcPlainText(plainText: String) {
        nfcInMemoryDataSource.setNfcPlainText(plainText)
    }

    override fun getNfcPlainText(): String {
        return nfcInMemoryDataSource.getNfcPlainText()
    }
}