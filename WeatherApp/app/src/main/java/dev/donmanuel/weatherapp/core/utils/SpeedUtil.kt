package dev.donmanuel.weatherapp.core.utils

/**
 * Utility object for formatting speed values.
 *
 * @property SpeedUtil
 */

object SpeedUtil {
    fun formatSpeed(speed: Double, isImperial: Boolean): String {
        val unit = if (isImperial) "mph" else "m/s"
        return "${DecimalUtil.format(speed)} $unit"
    }
}
