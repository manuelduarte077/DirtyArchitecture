package dev.donmanuel.weatherapp.domain.repository

import dev.donmanuel.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(
        query: String,
        units: String
    ): Weather?
}