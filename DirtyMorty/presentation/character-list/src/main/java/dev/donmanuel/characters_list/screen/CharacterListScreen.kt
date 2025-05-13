package dev.donmanuel.characters_list.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.donmanuel.characters_list.composables.CharacterItem
import dev.donmanuel.characters_list.model.CharacterUi
import dev.donmanuel.ui_kit.ErrorScreen
import dev.donmanuel.ui_kit.LoadingIndicator
import dev.donmanuel.ui_kit.ui.theme.Rick_and_MortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CharacterListScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onCharacterClick: (Long) -> Unit,
    onLoadMore: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Character list")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is UiState.Content -> CharacterListContent(
                    characters = uiState.characters,
                    onCharacterClick = onCharacterClick,
                    onLoadMore = onLoadMore
                )

                is UiState.Error -> ErrorScreen(
                    errorMessage = uiState.error,
                    onRetry = {}
                )

                UiState.Loading -> LoadingIndicator()
            }
        }
    }
}

@Composable
private fun CharacterListContent(
    modifier: Modifier = Modifier,
    characters: List<CharacterUi>,
    onCharacterClick: (Long) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItemsCount = layoutInfo.totalItemsCount
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItemIndex >= totalItemsCount - 5) {
                    onLoadMore()
                }
            }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = onCharacterClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterListContentPreview() {
    val character = CharacterUi(
        id = 44,
        name = "Body Guard Morty",
        status = "Dead",
        species = "Human",
        gender = "Male",
        origin = "unknown",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/44.jpeg"
    )
    val characters = listOf(character, character.copy(id = 1))
    Rick_and_MortyTheme {
        CharacterListContent(
            characters = characters,
            onCharacterClick = {},
            onLoadMore = {}
        )
    }
}
