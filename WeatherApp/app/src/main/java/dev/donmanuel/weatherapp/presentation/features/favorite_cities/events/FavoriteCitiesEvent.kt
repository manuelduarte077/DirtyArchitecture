package dev.donmanuel.weatherapp.presentation.features.favorite_cities.events

import dev.donmanuel.weatherapp.domain.model.FavoriteCity

sealed class FavoriteCitiesEvent {
    data class RemoveFavorite(val city: String) : FavoriteCitiesEvent()
    data class ShowDeleteDialog(val favoriteCity: FavoriteCity) : FavoriteCitiesEvent()
    data object DismissDeleteDialog : FavoriteCitiesEvent()
    data object RetryFetch : FavoriteCitiesEvent()
}
