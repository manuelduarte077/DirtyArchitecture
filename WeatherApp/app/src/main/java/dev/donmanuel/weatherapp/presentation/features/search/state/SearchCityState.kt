package dev.donmanuel.weatherapp.presentation.features.search.state

data class SearchCityState(
    val query: String = "",
    val isLoading: Boolean = false,
) {
    companion object {
        fun createToPreview() = SearchCityState(
            query = "Test value"
        )
    }
}
