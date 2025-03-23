package dev.donmanuel.weatherapp.data.model.response


data class WeatherResponse(
    val city: CityResponse,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItemResponse>,
)
