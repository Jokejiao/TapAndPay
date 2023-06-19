package com.codelab.tapandpay

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DataScreenViewModel @Inject constructor(
): ViewModel() {

    private var cardData: String = ""
    private var weatherData: String = ""

    var combinedData by mutableStateOf("")
        private set

    fun setCardData(data: String) {
        cardData = data
        combineData()
    }

    fun setWeatherData(data: String) {
        weatherData = data
        combineData()
    }

    private fun combineData() {
        if (cardData.isEmpty() || weatherData.isEmpty()) return
        combinedData = "Card Data:\n$cardData\nWeather Data:\n$weatherData"
    }
}