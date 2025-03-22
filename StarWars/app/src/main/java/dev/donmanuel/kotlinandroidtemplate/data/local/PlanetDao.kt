package dev.donmanuel.kotlinandroidtemplate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import dev.donmanuel.kotlinandroidtemplate.data.model.CachedPlanet

/**
 * Data Access Object (DAO) for managing planet-related database operations.
 */
@Dao
interface PlanetDao {

    /**
     * Inserts a list of planets into the database.
     * If a conflict occurs, the existing entry is replaced.
     *
     * @param planets The list of planets to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanets(planets: List<CachedPlanet>)

    /**
     * Retrieves all planets stored in the database.
     *
     * @return A [Flow] emitting the list of cached planets.
     */
    @Query("SELECT * FROM cached_planet")
    fun getAllPlanets(): Flow<List<CachedPlanet>>
}