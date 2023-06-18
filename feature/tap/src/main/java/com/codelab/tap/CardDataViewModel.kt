package com.codelab.tap

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.nfc.NfcRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardDataViewModel @Inject constructor(
    private val nfcRepository: NfcRepository
): ViewModel() {

    var cardData by mutableStateOf("")
        private set

    init {
        loadCardData()
    }

    private fun loadCardData() {
        viewModelScope.launch {
            cardData = nfcRepository.getNfcPlainText()
        }
    }
}