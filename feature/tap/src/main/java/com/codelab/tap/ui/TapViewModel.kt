package com.codelab.tap.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.nfcreader.NfcStatus
import com.codelab.nfcreader.SaveNfcPayloadUseCase
import com.codelab.nfcreader.StartNfcReaderUseCase
import com.codelab.nfcreader.StopNfcReaderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TapViewModel @Inject constructor(
    private val startNfcReaderUseCase: StartNfcReaderUseCase,
    private val stopNfcReaderUseCase: StopNfcReaderUseCase,
    private val saveNfcPayloadUseCase: SaveNfcPayloadUseCase,
) : ViewModel() {

    var nfcState by mutableStateOf(NfcStatus.NFC_OK)
        private set

    var dataState by mutableStateOf(false)
        private set

    fun startNfcReader(activity: Activity) {
        nfcState = startNfcReaderUseCase(activity)
    }

    fun stopNfcReader(activity: Activity) = stopNfcReaderUseCase(activity)

    fun saveNfcPayload(intent: Intent) {
        viewModelScope.launch {
            saveNfcPayloadUseCase(intent)
            dataState = true
        }
    }

    fun dataConsumed() {
        dataState = false
    }
}