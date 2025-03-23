package dev.donmanuel.data.sources.remote.models.characters


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersListDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val character: List<CharacterDto>
)