package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.response.WeatherItemResponse
import dev.donmanuel.weatherapp.domain.model.WeatherItem

fun List<WeatherItemResponse>.toWeatherItemList() = map { it.toWeatherItem() }

fun WeatherItemResponse.toWeatherItem() = WeatherItem(
    timestampDate = dt,
    humidity = humidity,
    pressure = pressure,
    speed = speed,
    sunrise = sunrise,
    sunset = sunset,
    weatherObject = weather[0].toWeatherObject(),
    temp = temp.toTemp()
)
