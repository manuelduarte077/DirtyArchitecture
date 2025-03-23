package dev.donmanuel.weatherapp.domain.model

data class City(
    val name: String,
    val country: String,
) {
    companion object {
        fun createToPreview() = City(
            name = "Masaya",
            country = "NI"
        )
    }
}
