package dev.donmanuel.kotlinandroidtemplate.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.kotlinandroidtemplate.data.local.PlanetDatabase
import dev.donmanuel.kotlinandroidtemplate.data.local.PlanetLocalDataSource
import dev.donmanuel.kotlinandroidtemplate.data.local.PlanetLocalDataSourceImpl
import dev.donmanuel.kotlinandroidtemplate.data.remote.PlanetApi
import dev.donmanuel.kotlinandroidtemplate.data.remote.PlanetRemoteDataSource
import dev.donmanuel.kotlinandroidtemplate.data.repository.PlanetRepositoryImpl
import dev.donmanuel.kotlinandroidtemplate.domain.repository.PlanetRepository
import dev.donmanuel.kotlinandroidtemplate.domain.usecase.GetNextPageUseCase
import dev.donmanuel.kotlinandroidtemplate.domain.usecase.GetPlanetsUseCase
import dev.donmanuel.kotlinandroidtemplate.data.remote.PlanetRemoteDataSourceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of [PlanetApi] used for making network requests.
     *
     * @return A [PlanetApi] instance for interacting with the SWAPI service.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): PlanetApi = Retrofit.Builder()
        .baseUrl("https://swapi.dev/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PlanetApi::class.java)

    /**
     * Provides an instance of [PlanetLocalDataSource], which manages local data storage.
     *
     * @param database The [PlanetDatabase] instance to provide the local data source.
     * @return A [PlanetLocalDataSource] implementation for managing local planet data.
     */
    @Provides
    @Singleton
    fun providePlanetLocalDataSource(database: PlanetDatabase): PlanetLocalDataSource {
        return PlanetLocalDataSourceImpl(database.planetDao())
    }

    /**
     * Provides an instance of [PlanetDatabase], which is used for Room database operations.
     *
     * @param context The application context used to build the Room database.
     * @return A [PlanetDatabase] instance.
     */
    @Provides
    @Singleton
    fun providePlanetDatabase(@ApplicationContext context: Context): PlanetDatabase {
        return Room.databaseBuilder(
            context,
            PlanetDatabase::class.java, "planets_database"
        ).build()
    }

    /**
     * Provides an instance of [PlanetRemoteDataSource] for fetching data from the remote API.
     *
     * @param planetRemoteDataSourceImpl The implementation of [PlanetRemoteDataSource].
     * @return A [PlanetRemoteDataSource] instance.
     */
    @Provides
    @Singleton
    fun providePlanetRemoteDataSource(planetRemoteDataSourceImpl: PlanetRemoteDataSourceImpl): PlanetRemoteDataSource =
        planetRemoteDataSourceImpl

    /**
     * Provides an instance of [PlanetRepository] used for interacting with both the local and remote data sources.
     *
     * @param planetRepositoryImpl The implementation of [PlanetRepository].
     * @return A [PlanetRepository] instance.
     */
    @Provides
    @Singleton
    fun providePlanetRepository(
        planetRepositoryImpl: PlanetRepositoryImpl
    ): PlanetRepository = planetRepositoryImpl

    /**
     * Provides an instance of [GetPlanetsUseCase], used to retrieve planet data.
     *
     * @param repository The [PlanetRepository] used by the use case.
     * @return A [GetPlanetsUseCase] instance for retrieving planet data.
     */
    @Provides
    @Singleton
    fun provideGetPlanetsUseCase(repository: PlanetRepository) = GetPlanetsUseCase(repository)


    /**
     * Provides an instance of [GetNextPageUseCase], used to fetch the next page of planet data.
     *
     * @param repository The [PlanetRepository] used by the use case.
     * @return A [GetNextPageUseCase] instance for fetching the next set of planet data.
     */
    @Provides
    @Singleton
    fun provideGetNextPageUseCase(repository: PlanetRepository) = GetNextPageUseCase(repository)

}