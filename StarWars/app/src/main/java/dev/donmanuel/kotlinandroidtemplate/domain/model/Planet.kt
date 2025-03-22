package dev.donmanuel.kotlinandroidtemplate.domain.model

import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetDto

data class Planet(
    val index: Int = 0,
    val name: String,
    val climate: String,
    val orbitalPeriod: String,
    val gravity: String
)


fun PlanetDto.toPlanet(): Planet {
    return Planet(
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity
    )
}
