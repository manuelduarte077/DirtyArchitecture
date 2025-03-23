package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.response.TempResponse
import dev.donmanuel.weatherapp.domain.model.Temp

fun TempResponse.toTemp() = Temp(
    day = day,
    min = min,
    max = max,
)
