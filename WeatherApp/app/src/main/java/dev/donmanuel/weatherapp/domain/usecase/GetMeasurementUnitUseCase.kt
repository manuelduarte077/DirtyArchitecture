package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.core.utils.Constants
import dev.donmanuel.weatherapp.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetMeasurementUnitUseCase {
    suspend operator fun invoke(): Flow<DataResult<String>>
}

class GetMeasurementUnitUseCaseImpl @Inject constructor(
    private val repository: MeasurementUnitRepository
) : GetMeasurementUnitUseCase {
    override suspend fun invoke(): Flow<DataResult<String>> = flow {
            emit(DataResult.Loading)
            try {
                repository.getMeasurementUnit().map { it ?: Constants.DEFAULT_MEASUREMENT_UNIT }
                    .collect {
                        emit(DataResult.Success(it))
                    }
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}