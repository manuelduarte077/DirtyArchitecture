package dev.donmanuel.weatherapp.data.repository

import dev.donmanuel.weatherapp.core.utils.NetworkUtil
import dev.donmanuel.weatherapp.data.mapper.toWeather
import dev.donmanuel.weatherapp.data.remote.api.WeatherService
import dev.donmanuel.weatherapp.domain.model.Weather
import dev.donmanuel.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    networkUtil: NetworkUtil,
    private val service: WeatherService
) : DataRepository(networkUtil), WeatherRepository {

    override suspend fun getWeatherByCity(
        query: String,
        units: String
    ): Weather? = fetchValidResponse(service.fetchWeatherByCity(query, units))?.toWeather()
}