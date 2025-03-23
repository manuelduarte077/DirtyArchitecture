package dev.donmanuel.character_details.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CharacterDetailsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsScreenViewModel =
        hiltViewModel<CharacterDetailsScreenViewModel>(),
    navigateToListScreen: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailsScreen(
        uiState = uiState,
        navigateToListScreen = navigateToListScreen
    )
}