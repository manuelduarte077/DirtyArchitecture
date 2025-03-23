package dev.donmanuel.weatherapp.presentation.features.home.state

import dev.donmanuel.weatherapp.domain.model.CurrentCity
import dev.donmanuel.weatherapp.domain.model.Weather

data class HomeState(
    val weather: Weather? = null,
    val currentCity: CurrentCity = CurrentCity(),
    val measurementUnit: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
) {
    companion object {
        fun createToPreview() = HomeState(
            weather = Weather.createToPreview(),
            currentCity = CurrentCity.createToPreview()
        )
    }
}
