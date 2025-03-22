package dev.donmanuel.kotlinandroidtemplate.data.repository

import dev.donmanuel.kotlinandroidtemplate.data.local.PlanetLocalDataSource
import dev.donmanuel.kotlinandroidtemplate.data.mapper.toCachedPlanet
import dev.donmanuel.kotlinandroidtemplate.data.mapper.toPlanetDto
import dev.donmanuel.kotlinandroidtemplate.data.model.PlanetResponse
import dev.donmanuel.kotlinandroidtemplate.data.remote.PlanetRemoteDataSource
import dev.donmanuel.kotlinandroidtemplate.domain.repository.PlanetRepository
import dev.donmanuel.kotlinandroidtemplate.utils.NoNetworkException
import dev.donmanuel.kotlinandroidtemplate.utils.RemoteDataSourceException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource,
    private val localDataSource: PlanetLocalDataSource
) : PlanetRepository {
    private var currentPageUrl: String? = null

    override fun getPlanets(): Flow<Result<PlanetResponse>> = flow<Result<PlanetResponse>> {
        try {
            remoteDataSource.getPlanets().collect { response ->
                currentPageUrl = response.next
                localDataSource.savePlanets(response.results.map {
                    it.toCachedPlanet(
                        extractPlanetId(it.url)
                    )
                })
                emit(Result.success(response))
            }
        } catch (e: NoNetworkException) {
            localDataSource.getPlanets().collect { result ->
                val cachedPlanets = result.map { it.toPlanetDto() }
                when {
                    cachedPlanets.isEmpty() -> emit(Result.failure(e))
                    else -> {
                        val cachedResponse = PlanetResponse(
                            next = currentPageUrl, results = cachedPlanets
                        )
                        emit(Result.success(cachedResponse))
                    }
                }
            }

        } catch (e: RemoteDataSourceException) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getNextPage(nextPageUrl: String): Flow<Result<PlanetResponse>> = flow {
        try {
            remoteDataSource.getNextPage(nextPageUrl).collect { response ->
                currentPageUrl = response.next
                localDataSource.savePlanets(response.results.map {
                    it.toCachedPlanet(
                        extractPlanetId(
                            it.url
                        )
                    )
                })
                emit(Result.success(response))
            }


        } catch (e: NoNetworkException) {
            emit(Result.failure(e))
        } catch (e: RemoteDataSourceException) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    private fun extractPlanetId(url: String): Int {
        return url.trimEnd('/').split("/").last().toInt()
    }

}