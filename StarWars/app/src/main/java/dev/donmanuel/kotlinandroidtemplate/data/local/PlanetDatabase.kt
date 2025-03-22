package dev.donmanuel.kotlinandroidtemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.donmanuel.kotlinandroidtemplate.data.model.CachedPlanet


@Database(entities = [CachedPlanet::class], version = 1)
abstract class PlanetDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao
}