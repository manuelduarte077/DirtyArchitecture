package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.response.WeatherResponse
import dev.donmanuel.weatherapp.domain.model.Weather

fun WeatherResponse.toWeather() = Weather(
    city = city.toCity(),
    weeklyWeatherList = list.toWeatherItemList(),
)
