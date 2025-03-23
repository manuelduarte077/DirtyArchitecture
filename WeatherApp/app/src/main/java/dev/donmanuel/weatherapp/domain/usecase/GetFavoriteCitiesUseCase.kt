package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.domain.model.FavoriteCity
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetFavoriteCitiesUseCase {
    suspend operator fun invoke(): Flow<DataResult<List<FavoriteCity>>>
}

class GetFavoriteCitiesUseCaseImpl(
    private val repository: FavoriteCityRepository
) : GetFavoriteCitiesUseCase {
    override suspend fun invoke(): Flow<DataResult<List<FavoriteCity>>> = flow {
        try {
            emit(DataResult.Loading)
            repository.getAll().collect { result ->
                emit(DataResult.Success(result))
            }
        } catch (e: Exception) {
            emit(DataResult.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
