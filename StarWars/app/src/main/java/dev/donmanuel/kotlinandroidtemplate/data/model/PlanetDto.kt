package dev.donmanuel.kotlinandroidtemplate.data.model

import com.google.gson.annotations.SerializedName

data class PlanetDto(
    val name: String,
    val climate: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val gravity: String,
    val url: String = ""
)