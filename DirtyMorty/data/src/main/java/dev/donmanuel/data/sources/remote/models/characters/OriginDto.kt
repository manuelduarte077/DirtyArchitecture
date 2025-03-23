package dev.donmanuel.data.sources.remote.models.characters


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)