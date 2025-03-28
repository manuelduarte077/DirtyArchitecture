package dev.donmanuel.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.data.repository.CharacterRepositoryImpl
import dev.donmanuel.domain.repositories.CharacterRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        impl: CharacterRepositoryImpl
    ): CharacterRepository
}