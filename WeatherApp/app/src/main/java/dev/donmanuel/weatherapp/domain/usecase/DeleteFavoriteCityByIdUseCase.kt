package dev.donmanuel.weatherapp.domain.usecase

import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface DeleteFavoriteCityByIdUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(val city: String)
}

class DeleteFavoriteCityByIdUseCaseImpl(
    private val repository: FavoriteCityRepository
) : DeleteFavoriteCityByIdUseCase {
    override suspend fun invoke(params: DeleteFavoriteCityByIdUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                emit(DataResult.Loading)
                emit(DataResult.Success(repository.deleteById(params.city)))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
