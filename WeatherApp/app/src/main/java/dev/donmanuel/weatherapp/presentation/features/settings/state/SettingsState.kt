package dev.donmanuel.weatherapp.presentation.features.settings.state

import dev.donmanuel.weatherapp.core.utils.Constants

data class SettingsState(
    val measurementUnit: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val showMeasurementUnitListDialog: Boolean = false
) {
    companion object {
        fun createToPreview() = SettingsState(
            measurementUnit = Constants.METRIC_MEASUREMENT_UNIT
        )
    }
}
