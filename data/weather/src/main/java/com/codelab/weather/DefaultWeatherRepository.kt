package com.codelab.weather

import com.codelab.weather.di.DefaultDispatcher
import com.codelab.weather.model.NetworkWeatherResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    weatherDataSource: WeatherDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : WeatherRepository {

    override val currentWeather: Flow<NetworkWeatherResource> =
        weatherDataSource.currentWeather
            .onEach {
                // TODO: save in cache
            }
            .flowOn(defaultDispatcher)
}