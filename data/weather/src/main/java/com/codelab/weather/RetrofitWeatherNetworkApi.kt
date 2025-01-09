package com.codelab.weather


import com.codelab.tapandpay.data.weather.BuildConfig
import com.codelab.weather.di.IoDispatcher
import com.codelab.weather.model.NetworkWeatherResource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


private const val WeatherBaseUrl = "https://api.weatherapi.com/"

/**
 * Retrofit API declaration for TAP Network API
 */
private interface RetrofitWeatherNetworkApi {
    @GET(value = "v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String, @Query("q") location: String,
        /*@Query("aqi") aqi: String = "no"*/
    ): NetworkWeatherResource
}

@Singleton
class RetrofitWeatherNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : WeatherDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(WeatherBaseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitWeatherNetworkApi::class.java)

    override val currentWeather = flow {
        emit(
            networkApi.getCurrentWeather(
                key = BuildConfig.WEATHER_API_KEY,
                location = "Auckland"
            )
        )
    }.flowOn(ioDispatcher)
}
