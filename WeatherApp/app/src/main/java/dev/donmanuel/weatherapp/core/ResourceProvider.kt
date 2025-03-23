package dev.donmanuel.weatherapp.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * A class that provides string resources from the application context.
 *
 * @param context The application context used to access string resources.
 */

class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)
}