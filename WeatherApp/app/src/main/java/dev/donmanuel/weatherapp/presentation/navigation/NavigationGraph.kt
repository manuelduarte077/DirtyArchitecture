package dev.donmanuel.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.FavoriteCitiesScreen
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.FavoriteCitiesViewModel
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.state.FavoriteCitiesState
import dev.donmanuel.weatherapp.presentation.features.home.HomeScreen
import dev.donmanuel.weatherapp.presentation.features.home.HomeViewModel
import dev.donmanuel.weatherapp.presentation.features.home.state.HomeState
import dev.donmanuel.weatherapp.presentation.features.search.SearchCityScreen
import dev.donmanuel.weatherapp.presentation.features.search.SearchCityViewModel
import dev.donmanuel.weatherapp.presentation.features.search.state.SearchCityState
import dev.donmanuel.weatherapp.presentation.features.settings.SettingsScreen
import dev.donmanuel.weatherapp.presentation.features.settings.SettingsViewModel
import dev.donmanuel.weatherapp.presentation.features.settings.state.SettingsState
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        NavigationManager.setNavController(navController)
    }

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState: HomeState = viewModel.uiState.collectAsState().value
            val toastEvent: SharedFlow<String> = viewModel.toastEvent

            HomeScreen(
                uiState = uiState,
                onEvent = { event -> viewModel.onEvent(event) },
                toastEvent = toastEvent
            )
        }

        composable(route = Screens.SearchCity.route) {
            val viewModel: SearchCityViewModel = hiltViewModel()
            val uiState: SearchCityState = viewModel.uiState.collectAsState().value
            val finishScreen: SharedFlow<Unit> = viewModel.finishScreen
            val toastEvent: SharedFlow<String> = viewModel.toastEvent

            SearchCityScreen(
                uiState = uiState,
                onEvent = { event -> viewModel.onEvent(event) },
                finishScreen = finishScreen,
                toastEvent = toastEvent
            )
        }

        composable(route = Screens.FavoriteCities.route) {
            val viewModel: FavoriteCitiesViewModel = hiltViewModel()
            val uiState: FavoriteCitiesState = viewModel.uiState.collectAsState().value
            val toastEvent: SharedFlow<String> = viewModel.toastEvent

            FavoriteCitiesScreen(
                uiState = uiState,
                onEvent = { event -> viewModel.onEvent(event) },
                toastEvent = toastEvent
            )
        }

        composable(route = Screens.Settings.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            val uiState: SettingsState = viewModel.uiState.collectAsState().value
            val finishScreen: SharedFlow<Unit> = viewModel.finishScreen
            val toastEvent: SharedFlow<String> = viewModel.toastEvent

            SettingsScreen(
                uiState = uiState,
                onEvent = { event -> viewModel.onEvent(event) },
                finishScreen = finishScreen,
                toastEvent = toastEvent
            )
        }
    }
}