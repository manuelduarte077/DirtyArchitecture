package dev.donmanuel.kotlinandroidtemplate.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetResponse
import dev.donmanuel.kotlinandroidtemplate.utils.NoNetworkException
import dev.donmanuel.kotlinandroidtemplate.utils.RemoteDataSourceException
import java.io.IOException
import javax.inject.Inject

class PlanetRemoteDataSourceImpl @Inject constructor(
    private val planetApi: PlanetApi
) : PlanetRemoteDataSource {
    override fun getPlanets(): Flow<PlanetResponse> = flow {
        try {
            emit(planetApi.getPlanets())
        } catch (e: Exception) {
            throw getRemoteException(e)
        }
    }.flowOn(Dispatchers.IO)

    override fun getNextPage(nextPageUrl: String): Flow<PlanetResponse> = flow {
        try {
            emit(planetApi.getNextPage(nextPageUrl))
        } catch (e: Exception) {
            throw getRemoteException(e)
        }
    }.flowOn(Dispatchers.IO)

    private fun getRemoteException(e: Exception): Exception {
        return when (e) {
            is IOException -> NoNetworkException()
            else -> RemoteDataSourceException(e.message ?: "Unknown error")
        }
    }
}