package com.codelab.weather

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