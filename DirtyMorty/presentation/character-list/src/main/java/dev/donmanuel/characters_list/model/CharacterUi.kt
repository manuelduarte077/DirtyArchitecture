package dev.donmanuel.characters_list.model

data class CharacterUi(
    val id: Long,
    val name: String,
    val status: String,
    val species:String,
    val gender: String,
    val origin:String,
    val imageUrl: String,
)