package dev.donmanuel.weatherapp.core.utils

/**
 * Utility object for formatting decimal values.
 */

object DecimalUtil {
    fun format(value: Double): String {
        return "%.0f".format(value)
    }
}