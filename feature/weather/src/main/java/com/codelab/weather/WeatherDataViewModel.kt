package com.codelab.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
//    private val _uiState: MutableStateFlow<String> = MutableStateFlow("")
//    val uiState: StateFlow<String> = _uiState.asStateFlow()

    val weatherState: StateFlow<String> = weatherRepository.currentWeather
        .asResult()
        .map {
            result ->
            when (result) {
                is Result.Success -> {
                    result.data.toString()
                }

                is Result.Loading -> {
                    "Loading"
                }

                is Result.Error -> {
                    "Network Error"
                }
            }
//        }
//        .map {it.toString()}
//        .catch { emit("Error") }.map {
//            it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    init {
//        viewModelScope.launch {
////            _uiState.value = WeatherUiState.Loading
//            _uiState.value = "Loading"
//            weatherRepository.currentWeather.catch {
////                _uiState.value = WeatherUiState.Error(it)
//                _uiState.value = "Error"
//            }.collect {
////                _uiState.value = WeatherUiState.Success(it.toString())
//                _uiState.value = it.toString()
//            }
//        }
    }
}

sealed interface WeatherUiState {
    data class Success(val weatherData: String) : WeatherUiState
    data class Error(val exception: Throwable) : WeatherUiState

    object Loading: WeatherUiState
}