package dev.donmanuel.weatherapp.domain.model

data class FavoriteCity(
    val city: String,
    val country: String,
) {
    companion object {
        fun createToPreview() = FavoriteCity(
            city = "Managua",
            country = "NI"
        )

        private fun createToPreview2() = FavoriteCity(
            city = "Dublin",
            country = "IE"
        )

        fun createListToPreview(): List<FavoriteCity> = listOf(
            createToPreview(),
            createToPreview2(),
            createToPreview(),
            createToPreview2()
        )
    }
}
