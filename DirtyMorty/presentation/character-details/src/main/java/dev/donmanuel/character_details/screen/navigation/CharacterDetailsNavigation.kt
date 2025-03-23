package dev.donmanuel.character_details.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.donmanuel.character_details.screen.CharacterDetailsScreenRoute

const val CHARACTER_DETAILS_ROUTE = "character_details"
const val CHARACTER_ID_ARG = "character_id"

fun NavController.navigateToDetails(
    characterId: Long
) {
    navigate(route = "$CHARACTER_DETAILS_ROUTE/$characterId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.characterDetailsScreen(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = "$CHARACTER_DETAILS_ROUTE/{$CHARACTER_ID_ARG}",
        arguments = listOf(
            navArgument(CHARACTER_ID_ARG) {
                type = NavType.LongType
            }
        )
    ) {
        CharacterDetailsScreenRoute(
            navigateToListScreen = navigateToListScreen
        )
    }
}