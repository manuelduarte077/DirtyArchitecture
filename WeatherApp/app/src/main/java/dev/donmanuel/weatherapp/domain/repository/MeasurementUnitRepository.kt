package dev.donmanuel.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface MeasurementUnitRepository {
    suspend fun saveMeasurementUnit(measurementUnit: String)
    suspend fun getMeasurementUnit(): Flow<String?>
}