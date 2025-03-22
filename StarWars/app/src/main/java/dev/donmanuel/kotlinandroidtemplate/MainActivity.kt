package dev.donmanuel.kotlinandroidtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.kotlinandroidtemplate.domain.model.Planet
import dev.donmanuel.kotlinandroidtemplate.presentation.route.args.PlanetDetailScreenArgs
import dev.donmanuel.kotlinandroidtemplate.presentation.route.args.PlanetListScreenArgs
import dev.donmanuel.kotlinandroidtemplate.presentation.route.args.SplashScreenArgs
import dev.donmanuel.kotlinandroidtemplate.presentation.screens.PlanetDetailsScreen
import dev.donmanuel.kotlinandroidtemplate.presentation.screens.PlanetsScreen
import dev.donmanuel.kotlinandroidtemplate.presentation.screens.SplashScreen
import dev.donmanuel.kotlinandroidtemplate.presentation.viewmodel.PlanetsViewModel
import dev.donmanuel.kotlinandroidtemplate.ui.theme.StarWarsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                val planetsViewModel: PlanetsViewModel = hiltViewModel()
                val navController = rememberNavController()
                val planetsState by planetsViewModel.planetsState.collectAsState()
                val isLoadingMore by planetsViewModel.isLoadingMore.collectAsState()


                NavHost(
                    navController = navController, startDestination = SplashScreenArgs
                ) {
                    composable<SplashScreenArgs> {
                        SplashScreen(onNavigateToPlanetsScreen = {
                            navController.navigate(PlanetListScreenArgs) {
                                popUpTo(SplashScreenArgs) { inclusive = true }
                            }
                        })
                    }
                    composable<PlanetListScreenArgs> {
                        PlanetsScreen(
                            planetsState = planetsState,
                            isLoadingMore = isLoadingMore,
                            onEvent = planetsViewModel::planetEvent,
                            navigateToPlanetDetails = { index, planet ->
                                navController.navigate(
                                    PlanetDetailScreenArgs(
                                        index = index,
                                        name = planet.name,
                                        orbitalPeriod = planet.orbitalPeriod,
                                        gravity = planet.gravity,
                                        climate = planet.climate
                                    )
                                )
                            }
                        )
                    }
                    composable<PlanetDetailScreenArgs> {
                        val args = it.toRoute<PlanetDetailScreenArgs>()
                        PlanetDetailsScreen(
                            planet = Planet(
                                index = args.index,
                                name = args.name,
                                orbitalPeriod = args.orbitalPeriod,
                                gravity = args.gravity,
                                climate = args.climate
                            ), onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

