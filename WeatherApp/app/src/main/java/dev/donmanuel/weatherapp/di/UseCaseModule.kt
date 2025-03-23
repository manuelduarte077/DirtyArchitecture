package dev.donmanuel.weatherapp.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.weatherapp.domain.repository.FavoriteCityRepository
import dev.donmanuel.weatherapp.domain.repository.MeasurementUnitRepository
import dev.donmanuel.weatherapp.domain.repository.WeatherRepository
import dev.donmanuel.weatherapp.domain.usecase.AddFavoriteCityUseCase
import dev.donmanuel.weatherapp.domain.usecase.AddFavoriteCityUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.DeleteFavoriteCityByIdUseCase
import dev.donmanuel.weatherapp.domain.usecase.DeleteFavoriteCityByIdUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.GetFavoriteCitiesUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetFavoriteCitiesUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.GetMeasurementUnitUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetMeasurementUnitUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherByCityUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherByCityUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherSettingsUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherSettingsUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.SaveCurrentCityUseCase
import dev.donmanuel.weatherapp.domain.usecase.SaveCurrentCityUseCaseImpl
import dev.donmanuel.weatherapp.domain.usecase.SaveMeasurementUnitUseCase
import dev.donmanuel.weatherapp.domain.usecase.SaveMeasurementUnitUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetWeatherByCityUseCase(repository: WeatherRepository): GetWeatherByCityUseCase =
        GetWeatherByCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetFavoriteCitiesUseCase(repository: FavoriteCityRepository): GetFavoriteCitiesUseCase =
        GetFavoriteCitiesUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideDeleteFavoriteCityByIdUseCase(repository: FavoriteCityRepository): DeleteFavoriteCityByIdUseCase =
        DeleteFavoriteCityByIdUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetWeatherSettingsUseCase(
        favoriteCityRepository: FavoriteCityRepository,
        measurementUnitRepository: MeasurementUnitRepository
    ): GetWeatherSettingsUseCase = GetWeatherSettingsUseCaseImpl(
        favoriteCityRepository = favoriteCityRepository,
        measurementUnitRepository = measurementUnitRepository
    )

    @Singleton
    @Provides
    fun provideAddFavoriteCityUseCase(repository: FavoriteCityRepository): AddFavoriteCityUseCase =
        AddFavoriteCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideSaveCurrentCityUseCase(repository: FavoriteCityRepository): SaveCurrentCityUseCase =
        SaveCurrentCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideSaveMeasurementUnitUseCase(repository: MeasurementUnitRepository): SaveMeasurementUnitUseCase =
        SaveMeasurementUnitUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetMeasurementUnitUseCase(repository: MeasurementUnitRepository): GetMeasurementUnitUseCase =
        GetMeasurementUnitUseCaseImpl(repository = repository)
}
