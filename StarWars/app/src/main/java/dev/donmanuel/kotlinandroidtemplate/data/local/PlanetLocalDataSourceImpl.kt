package dev.donmanuel.kotlinandroidtemplate.data.local

import kotlinx.coroutines.flow.Flow
import dev.donmanuel.kotlinandroidtemplate.data.model.CachedPlanet
import javax.inject.Inject

/**
 * Implementation of the [PlanetLocalDataSource] interface that provides local data source
 * operations for managing planet data using [PlanetDao].
 *
 * @constructor Creates an instance of [PlanetLocalDataSourceImpl] with the provided [planetDao].
 *
 * @param planetDao The data access object (DAO) for interacting with the local database to manage planet data.
 */
class PlanetLocalDataSourceImpl @Inject constructor(
    private val planetDao: PlanetDao
) : PlanetLocalDataSource {

    /**
     * Saves a list of planets to the local database.
     *
     * @param planets A list of [CachedPlanet] objects to be inserted into the database.
     */
    override fun savePlanets(planets: List<CachedPlanet>) {
        planetDao.insertPlanets(planets)
    }

    /**
     * Retrieves all the planets from the local database.
     *
     * @return A [Flow] that emits a list of [CachedPlanet] objects representing the planets in the database.
     */
    override fun getPlanets(): Flow<List<CachedPlanet>> {
        return planetDao.getAllPlanets()
    }
}