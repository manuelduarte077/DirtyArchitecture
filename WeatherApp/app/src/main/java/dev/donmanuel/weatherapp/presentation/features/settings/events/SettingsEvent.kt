package dev.donmanuel.weatherapp.presentation.features.settings.events

sealed class SettingsEvent {
    data class UpdateMeasurementUnit(val unit: String) : SettingsEvent()
    data object SaveMeasurementUnit : SettingsEvent()
    data object ShowMeasurementUnitListDialog : SettingsEvent()
    data object DismissMeasurementUnitListDialog : SettingsEvent()
    data object RetryFetch : SettingsEvent()
}