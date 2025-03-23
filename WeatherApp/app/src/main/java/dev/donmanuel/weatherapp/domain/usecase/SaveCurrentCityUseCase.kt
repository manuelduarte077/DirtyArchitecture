package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SaveCurrentCityUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(val city: String)
}

class SaveCurrentCityUseCaseImpl @Inject constructor(
    private val repository: FavoriteCityRepository
) : SaveCurrentCityUseCase {
    override suspend fun invoke(params: SaveCurrentCityUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                emit(DataResult.Loading)
                val result = repository.saveCurrentCity(params.city)
                emit(DataResult.Success(result))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
