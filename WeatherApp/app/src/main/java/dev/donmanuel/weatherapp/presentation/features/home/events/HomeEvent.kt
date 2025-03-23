package dev.donmanuel.weatherapp.presentation.features.home.events

sealed class HomeEvent {
    data object CheckedFavorite : HomeEvent()
    data object RetryFetch : HomeEvent()
}