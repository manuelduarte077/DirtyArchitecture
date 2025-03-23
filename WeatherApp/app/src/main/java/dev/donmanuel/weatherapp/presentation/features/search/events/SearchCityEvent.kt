package dev.donmanuel.weatherapp.presentation.features.search.events

sealed class SearchCityEvent {
    data class UpdateQuery(val query: String) : SearchCityEvent()
    data object ConfirmCity : SearchCityEvent()
}