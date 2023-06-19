package com.codelab.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkWeatherResource(
    val location: Location,
    val current: Current,
) {
    override fun toString(): String = StringBuilder()
        .append(location)
        .append("\n")
        .append(current)
        .toString()
}


@Serializable
data class Location(
    val lat: Float,
    val lon: Float,
    val name: String,
    val region: String,
    val country: String,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String,
) {
    override fun toString(): String = StringBuilder()
        .append("lat:$lat, ")
        .append("lon:$lon\n")
        .append("name:$name, ")
        .append("region:$region, ")
        .append("country:$country\n")
        .append("tz_id:$tz_id, ")
        .append("localtime_epoch:$localtime_epoch, ")
        .append("localtime:$localtime").toString()
}

@Serializable
data class Current(
//    val last_updated: String,
    val temp_c: Float,
    val condition: Condition,
//    val wind_kph: Float,
//    val wind_dir: String,
//    val precip_mm: Float,
//    val humidity: Int
//    val feelslike_c: Float,
    val uv: Float,
) {
    override fun toString(): String = StringBuilder()
        .append("temp_c:$temp_c, ")
        .append(condition)
        .append("uv:$uv")
        .toString()
}

@Serializable
data class Condition(
    val text: String
) {
    override fun toString(): String = StringBuilder().append("Condition text:$text, ").toString()
}
