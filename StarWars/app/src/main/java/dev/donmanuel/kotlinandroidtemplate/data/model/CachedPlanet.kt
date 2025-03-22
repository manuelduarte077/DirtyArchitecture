package dev.donmanuel.kotlinandroidtemplate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_planet")
data class CachedPlanet(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val climate: String,
    val orbitalPeriod: String,
    val gravity: String
)
