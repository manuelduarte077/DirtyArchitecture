package dev.donmanuel.domain.usecases

import dev.donmanuel.domain.model.Character
import dev.donmanuel.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Character>> {
        return try {
            Result.success(characterRepository.getAllCharacters(page))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}