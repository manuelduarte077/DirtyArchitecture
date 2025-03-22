package dev.donmanuel.kotlinandroidtemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetResponse

interface PlanetRepository {
    fun getPlanets(): Flow<Result<PlanetResponse>>
    fun getNextPage(nextPageUrl: String): Flow<Result<PlanetResponse>>
}