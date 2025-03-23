package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.response.WeatherObjectResponse
import dev.donmanuel.weatherapp.domain.model.WeatherObject

fun WeatherObjectResponse.toWeatherObject() = WeatherObject(
    id = id,
    description = description,
    icon = icon,
    condition = main
)
