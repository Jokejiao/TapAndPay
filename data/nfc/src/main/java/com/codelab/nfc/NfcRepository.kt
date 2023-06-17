package com.codelab.nfc

interface NfcRepository {
    fun startNfcReader()

    fun stopNfcReader()
}