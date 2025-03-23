package dev.donmanuel.weatherapp.domain.model

data class Weather(
    val city: City,
    val weeklyWeatherList: List<WeatherItem>
) {
    companion object {
        fun createToPreview() = Weather(
            city = City.Companion.createToPreview(),
            weeklyWeatherList = WeatherItem.createListToPreview()
        )
    }
}
