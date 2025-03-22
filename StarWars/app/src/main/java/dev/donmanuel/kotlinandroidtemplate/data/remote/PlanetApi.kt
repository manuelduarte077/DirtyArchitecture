package dev.donmanuel.kotlinandroidtemplate.data.remote

import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interface defining the API endpoints for interacting with the planets data.
 * The interface is used for making network requests to retrieve planets information.
 */
interface PlanetApi {

    /**
     * Fetches a list of planets from the API.
     *
     * @return A [PlanetResponse] object containing the details of planets.
     */
    @GET("planets")
    suspend fun getPlanets(): PlanetResponse

    /**
     * Fetches the next page of planet data from the API using a provided URL.
     *
     * @param nextPageUrl The URL of the next page of data to be fetched.
     * @return A [PlanetResponse] object containing the details of the next set of planets.
     */
    @GET
    suspend fun getNextPage(@Url nextPageUrl: String): PlanetResponse
}