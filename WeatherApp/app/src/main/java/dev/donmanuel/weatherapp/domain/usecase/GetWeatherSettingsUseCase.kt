package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.core.utils.Constants
import dev.donmanuel.weatherapp.domain.model.CurrentCity
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import dev.donmanuel.weatherapp.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface GetWeatherSettingsUseCase {
    suspend operator fun invoke(): Flow<DataResult<Pair<CurrentCity, String>>>
}

class GetWeatherSettingsUseCaseImpl(
    private val favoriteCityRepository: FavoriteCityRepository,
    private val measurementUnitRepository: MeasurementUnitRepository
) : GetWeatherSettingsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<DataResult<Pair<CurrentCity, String>>> = flow {
        emit(DataResult.Loading)
    }.flatMapLatest {
        combine(
            favoriteCityRepository.getCurrentCity(),
            measurementUnitRepository.getMeasurementUnit()
        ) { city, measurementUnit ->
            val safeCity = city ?: Constants.DEFAULT_CITY
            val safeMeasurementUnit = measurementUnit ?: Constants.DEFAULT_MEASUREMENT_UNIT
            safeCity to safeMeasurementUnit
        }.flatMapLatest { (city, unit) ->
            favoriteCityRepository.isFavorite(city).map { isFavorite ->
                DataResult.Success(Pair(CurrentCity(city, isFavorite), unit))
            }
        }
    }.catch { e ->
        DataResult.Failure(e as? Exception ?: Exception(e))
    }.flowOn(Dispatchers.IO)
}
