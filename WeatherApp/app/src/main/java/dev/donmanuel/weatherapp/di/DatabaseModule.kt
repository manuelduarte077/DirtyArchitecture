package dev.donmanuel.weatherapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.weatherapp.data.local.data_store.DataStoreManager
import dev.donmanuel.weatherapp.data.local.database.FavoriteCityDao
import dev.donmanuel.weatherapp.data.local.database.WeatherDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context = context,
            klass = WeatherDatabase::class.java,
            name = "weather_database.db3"
        ).build()

    @Singleton
    @Provides
    fun provideFavoriteCityDao(database: WeatherDatabase): FavoriteCityDao =
        database.FavoriteCityDao()
}
