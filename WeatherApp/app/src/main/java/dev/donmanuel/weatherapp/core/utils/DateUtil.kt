package dev.donmanuel.weatherapp.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Utility object for date formatting.
 *
 * This object provides methods to format timestamps into human-readable date and time strings.
 */

object DateUtil {
    fun formatDate(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun formatDateTime(timestamp: Int): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun getAbbreviatedDayOfWeek(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun getDayOfWeek(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }
}
