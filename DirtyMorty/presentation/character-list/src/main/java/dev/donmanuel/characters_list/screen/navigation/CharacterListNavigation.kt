package dev.donmanuel.characters_list.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.donmanuel.characters_list.screen.CharacterListScreenRoute

const val CHARACTER_LIST_SCREEN_ROUTE = "character_list_screen"

fun NavController.navigateToList() = navigate(CHARACTER_LIST_SCREEN_ROUTE) {
    popUpTo(CHARACTER_LIST_SCREEN_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.characterListScreen(
    navigateToDetailsScreen: (Long) -> Unit
) {
    composable(route = CHARACTER_LIST_SCREEN_ROUTE) {
        CharacterListScreenRoute(navigateToDetailsScreen = navigateToDetailsScreen)
    }
}