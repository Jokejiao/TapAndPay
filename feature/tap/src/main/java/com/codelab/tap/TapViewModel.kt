package com.codelab.tap

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codelab.nfc.NfcRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TapViewModel @Inject constructor(
    private val nfcRepository: NfcRepository
) : ViewModel() {

    init {
        Log.i("Alex", "abc")

    }
}