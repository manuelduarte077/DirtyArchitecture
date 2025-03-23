package dev.donmanuel.data.sources.remote.models.characters


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("created")
    val created: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Long,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val locationDto: LocationDto,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val originDto: OriginDto,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)