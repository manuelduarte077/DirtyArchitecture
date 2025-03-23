package dev.donmanuel.domain.model

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species:String,
    val gender: String,
    val origin:String,
    val imageUrl: String,
)