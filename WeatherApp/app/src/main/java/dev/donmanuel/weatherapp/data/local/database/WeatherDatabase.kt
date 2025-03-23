package dev.donmanuel.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.donmanuel.weatherapp.data.model.entity.FavoriteCityEntity

@Database(
    entities = [FavoriteCityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun FavoriteCityDao(): FavoriteCityDao
}
