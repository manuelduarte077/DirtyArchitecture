package dev.donmanuel.weatherapp.presentation.features.favorite_cities.state

import dev.donmanuel.weatherapp.domain.model.FavoriteCity

data class FavoriteCitiesState(
    val favoriteCitiesList: List<FavoriteCity> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val showConfirmDialog: Boolean = false,
    val cityToDelete: FavoriteCity? = null
) {
    companion object {
        fun createToPreview() = FavoriteCitiesState(
            favoriteCitiesList = FavoriteCity.createListToPreview(),
        )

        fun createToConfirmDeletePreview() = FavoriteCitiesState(
            favoriteCitiesList = FavoriteCity.createListToPreview(),
            showConfirmDialog = true,
            cityToDelete = FavoriteCity.createToPreview()
        )
    }
}
