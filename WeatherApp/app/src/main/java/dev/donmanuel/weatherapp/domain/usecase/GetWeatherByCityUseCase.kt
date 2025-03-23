package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.core.exception.NetworkException
import dev.donmanuel.weatherapp.domain.model.Weather
import dev.donmanuel.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetWeatherByCityUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Weather?>>
    data class Params(val query: String, val units: String)
}

class GetWeatherByCityUseCaseImpl @Inject constructor(
    private val repository: WeatherRepository
) : GetWeatherByCityUseCase {
    override suspend fun invoke(params: GetWeatherByCityUseCase.Params): Flow<DataResult<Weather?>> =
        flow {
            emit(DataResult.Loading)
            try {
                val weather = repository.getWeatherByCity(params.query, params.units)
                emit(DataResult.Success(weather))
            } catch (e: NetworkException) {
                emit(DataResult.Failure(e))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}