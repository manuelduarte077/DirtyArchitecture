package dev.donmanuel.booklibrary.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String,
    val title: String,
    val summary: String,
    val isFavorite: Boolean,
    val category: String,
    val tags: List<String>,
    val author: String
)
