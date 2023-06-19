package com.codelab.weather

import com.codelab.weather.model.NetworkWeatherResource
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

//    suspend fun getCurrentWeather(): Flow<NetworkWeatherResource>
    val currentWeather: Flow<NetworkWeatherResource>
}