package dev.donmanuel.data.sources.remote.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import dev.donmanuel.data.sources.remote.models.characters.CharacterDto
import dev.donmanuel.data.sources.remote.models.characters.CharactersListDto

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): CharactersListDto

    @GET("character/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Long
    ): CharacterDto
}