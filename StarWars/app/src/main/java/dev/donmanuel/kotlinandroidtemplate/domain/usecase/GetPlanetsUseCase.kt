package dev.donmanuel.kotlinandroidtemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.donmanuel.kotlinandroidtemplate.domain.model.PlanetPageData
import dev.donmanuel.kotlinandroidtemplate.domain.model.toPlanetPageData
import dev.donmanuel.kotlinandroidtemplate.domain.repository.PlanetRepository

class GetPlanetsUseCase(private val repository: PlanetRepository) {
    operator fun invoke(): Flow<Result<PlanetPageData>> =
        repository.getPlanets().map { result ->
            result.mapCatching { it.toPlanetPageData() }
        }
}