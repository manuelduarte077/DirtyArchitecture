package dev.donmanuel.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.weatherapp.core.utils.NetworkUtil
import dev.donmanuel.weatherapp.data.local.data_store.DataStoreManager
import dev.donmanuel.weatherapp.data.local.database.FavoriteCityDao
import dev.donmanuel.weatherapp.data.remote.api.WeatherService
import dev.donmanuel.weatherapp.data.repository.FavoriteCityRepositoryImpl
import dev.donmanuel.weatherapp.data.repository.MeasurementUnitRepositoryImpl
import dev.donmanuel.weatherapp.data.repository.WeatherRepositoryImpl
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import dev.donmanuel.weatherapp.domain.repository.MeasurementUnitRepository
import dev.donmanuel.weatherapp.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        networkUtil: NetworkUtil,
        service: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(
        networkUtil = networkUtil,
        service = service
    )

    @Singleton
    @Provides
    fun provideFavoriteCityRepository(
        dao: FavoriteCityDao,
        dataStoreManager: DataStoreManager
    ): FavoriteCityRepository =
        FavoriteCityRepositoryImpl(
            dao = dao,
            dataStoreManager = dataStoreManager
        )

    @Singleton
    @Provides
    fun provideMeasurementUnitRepository(dataStoreManager: DataStoreManager): MeasurementUnitRepository =
        MeasurementUnitRepositoryImpl(dataStoreManager = dataStoreManager)
}
