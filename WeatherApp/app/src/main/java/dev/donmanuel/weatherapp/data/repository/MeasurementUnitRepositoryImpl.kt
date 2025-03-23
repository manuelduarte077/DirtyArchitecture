package dev.donmanuel.weatherapp.data.repository

import dev.donmanuel.weatherapp.data.local.data_store.DataStoreManager
import dev.donmanuel.weatherapp.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeasurementUnitRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : MeasurementUnitRepository {
    override suspend fun saveMeasurementUnit(measurementUnit: String) {
        dataStoreManager.saveCurrentMeasurementUnit(measurementUnit)
    }

    override suspend fun getMeasurementUnit(): Flow<String?> =
        dataStoreManager.currentMeasurementUnitFlow
}
