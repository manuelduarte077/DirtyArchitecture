package dev.donmanuel.weatherapp.data.repository

import dev.donmanuel.weatherapp.data.local.data_store.DataStoreManager
import dev.donmanuel.weatherapp.data.local.database.FavoriteCityDao
import dev.donmanuel.weatherapp.data.mapper.toFavoriteCityEntity
import dev.donmanuel.weatherapp.data.mapper.toFavoriteCityList
import dev.donmanuel.weatherapp.domain.model.FavoriteCity
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteCityRepositoryImpl @Inject constructor(
    private val dao: FavoriteCityDao,
    private val dataStoreManager: DataStoreManager
) : FavoriteCityRepository {
    override suspend fun getAll(): Flow<List<FavoriteCity>> =
        dao.selectAll().map { list -> list.toFavoriteCityList() }

    override suspend fun save(favoriteCity: FavoriteCity) {
        dao.insert(favoriteCity.toFavoriteCityEntity())
    }

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun deleteById(city: String) = dao.deleteById(city)

    override suspend fun saveCurrentCity(city: String) {
        dataStoreManager.saveCurrentCity(city)
    }

    override suspend fun getCurrentCity(): Flow<String?> = dataStoreManager.currentCityFlow

    override suspend fun isFavorite(city: String): Flow<Boolean> =
        dao.countById(city).map { it > 0 }
}
