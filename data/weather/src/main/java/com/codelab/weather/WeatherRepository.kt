package com.codelab.weather

import com.codelab.weather.model.NetworkWeatherResource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

//    suspend fun getCurrentWeather(): Flow<String>
    val currentWeather: Flow<NetworkWeatherResource>
}