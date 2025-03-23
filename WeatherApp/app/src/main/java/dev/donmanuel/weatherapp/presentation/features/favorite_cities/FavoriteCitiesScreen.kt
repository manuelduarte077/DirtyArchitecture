package dev.donmanuel.weatherapp.presentation.features.favorite_cities

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.events.FavoriteCitiesEvent
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.state.FavoriteCitiesState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun FavoriteCitiesScreen(
    uiState: FavoriteCitiesState,
    onEvent: (FavoriteCitiesEvent) -> Unit,
    toastEvent: SharedFlow<String>
) {
    FavoriteCitiesContent(
        uiState = uiState,
        onEvent = onEvent,
        toastEvent = toastEvent
    )
}

@Preview
@Composable
private fun Preview() {
    FavoriteCitiesScreen(
        uiState = FavoriteCitiesState.createToPreview(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}
