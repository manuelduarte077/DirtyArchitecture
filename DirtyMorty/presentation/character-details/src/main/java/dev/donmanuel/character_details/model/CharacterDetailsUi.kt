package dev.donmanuel.character_details.model

data class CharacterDetailsUi(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val episode: List<String>,
    val location: String,
    val imageUrl: String,
)