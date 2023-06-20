package com.codelab.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.weather.Result
import com.codelab.weather.WeatherRepository
import com.codelab.weather.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class WeatherDataViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

//    private val _uiState: MutableStateFlow<WeatherUiState> =
//        MutableStateFlow(WeatherUiState.Success(""))
//    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var weatherData = ""

    // TODO: Return UI states instead of Strings
    val weatherState: StateFlow<String> = weatherRepository.currentWeather
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    weatherData = result.data.toString()
                    invokeCallback()
                    weatherData
                }

                is Result.Loading -> {
                    "Loading..."
                }

                is Result.Error -> {
                    "Network Error"
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeout),
            initialValue = ""
        )

//    init {
//        viewModelScope.launch {
//            _uiState.value = WeatherUiState.Loading
//            weatherRepository.currentWeather.catch {
//                _uiState.value = WeatherUiState.Error(it)
//            }.collect {
//                _uiState.value = WeatherUiState.Success(it.toString())
//            }
//        }
//    }

    private var dataCallback: ((String) -> Unit)? = null

    fun setDataCallback(cb: (String) -> Unit) {
        dataCallback = cb
        invokeCallback()
    }

    private fun invokeCallback() {
        if (weatherData.isNotEmpty()) dataCallback?.invoke(weatherData)
    }

    companion object {
        const val stopTimeout = 5000L
    }
}

/*
sealed interface WeatherUiState {
    data class Success(val weatherData: String) : WeatherUiState
    data class Error(val exception: Throwable) : WeatherUiState

    object Loading: WeatherUiState
}
*/
