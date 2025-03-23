package dev.donmanuel.character_details.mapper

import dev.donmanuel.character_details.model.CharacterDetailsUi
import dev.donmanuel.domain.model.CharacterDetails

fun CharacterDetails.toCharacterUi(): CharacterDetailsUi =
    CharacterDetailsUi(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        episode = episode,
        location = location,
        imageUrl = imageUrl
    )