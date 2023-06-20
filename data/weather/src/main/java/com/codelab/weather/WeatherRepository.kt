package com.codelab.weather

import com.codelab.weather.model.NetworkWeatherResource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    val currentWeather: Flow<NetworkWeatherResource>
}