package dev.donmanuel.data.repository

import dev.donmanuel.data.sources.remote.mappers.toCharacter
import dev.donmanuel.data.sources.remote.mappers.toCharacterDetails
import dev.donmanuel.data.sources.remote.service.RickAndMortyApi
import dev.donmanuel.domain.model.Character
import dev.donmanuel.domain.model.CharacterDetails
import dev.donmanuel.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getAllCharacters(page: Int): List<Character> {
        val characters = api.getAllCharacters(page).character

        return characters.map { character ->
            character.toCharacter()
        }
    }

    override suspend fun getCharacterDetails(id: Long): CharacterDetails {
        val character = api.getCharacterDetails(id)

        return character.toCharacterDetails()
    }
}