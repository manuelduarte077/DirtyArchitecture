package dev.donmanuel.domain.usecases

import dev.donmanuel.domain.model.CharacterDetails
import dev.donmanuel.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharactersDetailsUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(id: Long): Result<CharacterDetails> {
        return try {
            Result.success(characterRepository.getCharacterDetails(id = id))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}