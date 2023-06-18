package com.codelab.nfc

import javax.inject.Inject


class DefaultNfcRepository @Inject constructor(
  private val nfcInMemoryDataSource: NfcInMemoryDataSource
) : NfcRepository {

    override suspend fun setNfcPlainText(plainText: String) {
        nfcInMemoryDataSource.setNfcPlainText(plainText)
    }

    override suspend fun getNfcPlainText(): String {
        return nfcInMemoryDataSource.getNfcPlainText()
    }
}