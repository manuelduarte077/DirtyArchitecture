package dev.donmanuel.kotlinandroidtemplate.data.model

data class PlanetResponse(
    val next: String?,
    val results: List<PlanetDto>
)