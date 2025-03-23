package dev.donmanuel.data.sources.remote.mappers

import dev.donmanuel.data.sources.remote.models.characters.CharacterDto
import dev.donmanuel.domain.model.Character
import dev.donmanuel.domain.model.CharacterDetails

fun CharacterDto.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        origin = originDto.name,
        imageUrl = image
    )

fun CharacterDto.toCharacterDetails(): CharacterDetails =
    CharacterDetails(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = originDto.name,
        episode = episode,
        location = locationDto.name,
        imageUrl = image
    )
