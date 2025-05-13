package dev.donmanuel.domain.repositories

import dev.donmanuel.domain.model.Character
import dev.donmanuel.domain.model.CharacterDetails

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int): List<Character>

    suspend fun getCharacterDetails(id: Long): CharacterDetails
}