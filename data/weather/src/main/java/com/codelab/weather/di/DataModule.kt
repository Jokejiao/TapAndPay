package com.codelab.weather.di

import com.codelab.weather.DefaultWeatherRepository
import com.codelab.weather.RetrofitWeatherNetwork
import com.codelab.weather.WeatherDataSource
import com.codelab.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun RetrofitWeatherNetwork.binds(): WeatherDataSource

    @Binds
    fun bindsWeatherRepository(nfcRepository: DefaultWeatherRepository,
    ): WeatherRepository
}