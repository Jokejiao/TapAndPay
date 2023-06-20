package com.codelab.weather

import com.codelab.weather.model.NetworkWeatherResource
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    val currentWeather: Flow<NetworkWeatherResource>
}