package dev.donmanuel.kotlinandroidtemplate.data.remote

import kotlinx.coroutines.flow.Flow
import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetResponse

/**
 * Interface defining the contract for the remote data source responsible for
 * fetching planet data from a remote source (e.g., an API).
 */
interface PlanetRemoteDataSource {

    /**
     * Fetches a list of planets from the remote data source.
     *
     * @return A [Flow] emitting the [PlanetResponse] object containing the planet data.
     */
    fun getPlanets(): Flow<PlanetResponse>

    /**
     * Fetches the next page of planet data from the remote data source using the provided URL.
     *
     * @param nextPageUrl The URL of the next page to fetch planet data from.
     * @return A [Flow] emitting the [PlanetResponse] object containing the next set of planet data.
     */
    fun getNextPage(nextPageUrl: String): Flow<PlanetResponse>
}