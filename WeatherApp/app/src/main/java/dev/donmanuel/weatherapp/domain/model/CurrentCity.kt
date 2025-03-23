package dev.donmanuel.weatherapp.domain.model

data class CurrentCity(
    val name: String = "",
    val isFavorite: Boolean = false
) {
    companion object {
        fun createToPreview() = CurrentCity(
            name = "São Paulo",
            isFavorite = true
        )
    }
}
