package dev.donmanuel.characters_list.mapper

import dev.donmanuel.characters_list.model.CharacterUi
import dev.donmanuel.domain.model.Character

fun Character.toCharacterUi(): CharacterUi =
    CharacterUi(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        origin = origin,
        imageUrl = imageUrl
    )