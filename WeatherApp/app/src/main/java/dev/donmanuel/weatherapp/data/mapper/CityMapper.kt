package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.response.CityResponse
import dev.donmanuel.weatherapp.domain.model.City

fun CityResponse.toCity() = City(
    name = name,
    country = country
)
