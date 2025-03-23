package dev.donmanuel.rick_and_morty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.character_details.screen.navigation.characterDetailsScreen
import dev.donmanuel.character_details.screen.navigation.navigateToDetails
import dev.donmanuel.characters_list.screen.navigation.CHARACTER_LIST_SCREEN_ROUTE
import dev.donmanuel.characters_list.screen.navigation.characterListScreen
import dev.donmanuel.characters_list.screen.navigation.navigateToList

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CHARACTER_LIST_SCREEN_ROUTE
    ) {
        characterListScreen(
            navigateToDetailsScreen = {
                navController.navigateToDetails(it)
            }
        )

        characterDetailsScreen(
            navigateToListScreen = {
                navController.navigateToList()
            }
        )
    }
}