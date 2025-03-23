package dev.donmanuel.weatherapp.core.utils

/**
 * A utility object that provides constants and helper functions for API interactions.
 *
 * This object contains base URLs, API endpoints, and other constants used in network requests.
 */

object ApiUtil {

    const val BASE_URL = "https://api.openweathermap.org"
    private const val BASE_IMAGE_URL = "https://openweathermap.org"

    const val PREFIX_API_DATA = "data"
    const val FORECAST_DAILY_API = "forecast/daily"

    const val V2_5 = "2.5"

    const val QUERY_API_PARAM = "q"
    const val UNITS_API_PARAM = "units"
    const val APP_ID_API_PARAM = "APPID"

    const val UNITS_API_PARAM_DEFAULT_VALUE = "metric"

    fun buildIconUrl(icon: String): String = "${BASE_IMAGE_URL}/img/wn/${icon}.png"
}