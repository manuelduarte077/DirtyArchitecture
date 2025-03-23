package dev.donmanuel.weatherapp.presentation.features.favorite_cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.donmanuel.weatherapp.presentation.components.ConfirmDeleteDialog
import dev.donmanuel.weatherapp.presentation.components.CustomToast
import dev.donmanuel.weatherapp.presentation.components.CustomTopAppBar
import dev.donmanuel.weatherapp.presentation.components.ErrorComp
import dev.donmanuel.weatherapp.presentation.components.LoadingIndicator
import dev.donmanuel.weatherapp.R
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.components.FavoriteCitiesList
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.events.FavoriteCitiesEvent
import dev.donmanuel.weatherapp.presentation.features.favorite_cities.state.FavoriteCitiesState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun FavoriteCitiesContent(
    uiState: FavoriteCitiesState,
    onEvent: (FavoriteCitiesEvent) -> Unit,
    toastEvent: SharedFlow<String>
) {
    var toastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        toastEvent.collect { message -> toastMessage = message }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = stringResource(R.string.favorite_cities))
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {
            when {
                uiState.errorMessage.isNotEmpty() -> ErrorComp(errorMessage = uiState.errorMessage) {
                    onEvent(FavoriteCitiesEvent.RetryFetch)
                }

                uiState.isLoading -> LoadingIndicator()
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                    ) {
                        FavoriteCitiesList(
                            data = uiState.favoriteCitiesList,
                            onDeleteItem = { favoriteCity ->
                                onEvent(FavoriteCitiesEvent.ShowDeleteDialog(favoriteCity))
                            }
                        )
                    }
                }
            }

            uiState.cityToDelete?.takeIf { uiState.showConfirmDialog }?.let {
                ConfirmDeleteDialog(
                    title = stringResource(R.string.confirm_deletion),
                    message = stringResource(R.string.confirm_remove_favorite_city, it.city),
                    onConfirm = { onEvent(FavoriteCitiesEvent.RemoveFavorite(it.city)) },
                    onDismiss = { onEvent(FavoriteCitiesEvent.DismissDeleteDialog) }
                )
            }

            toastMessage?.let { message ->
                CustomToast(message) { toastMessage = null }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavoriteCitiesContent(
        uiState = FavoriteCitiesState.createToPreview(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}

@Preview
@Composable
private fun ConfirmDeletePreview() {
    FavoriteCitiesContent(
        uiState = FavoriteCitiesState.createToConfirmDeletePreview(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}
