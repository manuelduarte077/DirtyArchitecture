package dev.donmanuel.weatherapp.data.model.response


data class WeatherObjectResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)