package dev.donmanuel.kotlinandroidtemplate.data.mapper

import dev.donmanuel.kotlinandroidtemplate.data.model.CachedPlanet
import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetDto

fun PlanetDto.toCachedPlanet(id: Int = 0): CachedPlanet {
    return CachedPlanet(
        id = id,
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity
    )
}

fun CachedPlanet.toPlanetDto(): PlanetDto {
    return PlanetDto(
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity
    )
}