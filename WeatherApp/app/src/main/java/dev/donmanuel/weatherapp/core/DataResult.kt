package dev.donmanuel.weatherapp.core

/**
 * A sealed class representing the result of a data operation.
 *
 * @param T The type of data returned on success.
 */

sealed class DataResult<out T> {
    data object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T?) : DataResult<T>()
    data class Failure(val e: Exception?) : DataResult<Nothing>()
}