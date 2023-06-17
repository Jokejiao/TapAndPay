package com.codelab.nfcreader

import android.app.Activity
import android.nfc.NfcAdapter
import javax.inject.Inject

class StopNfcReaderUseCase @Inject constructor(
) {

    operator fun invoke(activity: Activity) {
        NfcAdapter.getDefaultAdapter(activity)?.disableForegroundDispatch(activity)
    }
}