package com.codelab.weather


import com.codelab.weather.model.NetworkWeatherResource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Retrofit API declaration for NIA Network API
 */
private interface RetrofitWeatherNetworkApi {
    @GET(value = "v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String, @Query("q") location: String,
        /*@Query("aqi") aqi: String = "no"*/
    ): NetworkWeatherResource
}

private const val WeatherBaseUrl = "https://api.weatherapi.com/"

/**
 * Wrapper for data provided from the [NiaBaseUrl]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

/**
 * [Retrofit] backed [NiaNetworkDataSource]
 */
@Singleton
class RetrofitWeatherNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    @ApplicationScope externalScope: CoroutineScope,
) : WeatherDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(WeatherBaseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitWeatherNetworkApi::class.java)

    //    override suspend fun getCurrentWeather(): Flow<NetworkWeatherResource> = flow {
//        emit(
//            networkApi.getCurrentWeather(
//                key = "4e083895d01a427faf3184618231806",
//                location = "Auckland"
//            )
//        )
//    }
    override val currentWeather = flow {
        emit(
            networkApi.getCurrentWeather(
                key = "4e083895d01a427faf3184618231806",
                location = "Auckland"
            )
        )
    }.flowOn(ioDispatcher)/*.shareIn(
        externalScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )*/


//    override suspend fun getTopics(ids: List<String>?): List<NetworkTopic> =
//        networkApi.getTopics(ids = ids).data
//
//    override suspend fun getNewsResources(ids: List<String>?): List<NetworkNewsResource> =
//        networkApi.getNewsResources(ids = ids).data
//
//    override suspend fun getTopicChangeList(after: Int?): List<NetworkChangeList> =
//        networkApi.getTopicChangeList(after = after)
//
//    override suspend fun getNewsResourceChangeList(after: Int?): List<NetworkChangeList> =
//        networkApi.getNewsResourcesChangeList(after = after)
}
