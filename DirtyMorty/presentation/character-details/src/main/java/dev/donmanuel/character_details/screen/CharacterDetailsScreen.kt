package dev.donmanuel.character_details.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.donmanuel.character_details.model.CharacterDetailsUi
import dev.donmanuel.ui_kit.ErrorScreen
import dev.donmanuel.ui_kit.LoadingIndicator
import dev.donmanuel.ui_kit.ui.theme.Rick_and_MortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    navigateToListScreen: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Character details") },
                navigationIcon = {
                    IconButton(
                        onClick = navigateToListScreen,
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    )
                }
            )
        }

    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is UiState.Content -> CharacterDetailsContent(
                    character = uiState.character
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
private fun CharacterDetailsContent(
    modifier: Modifier = Modifier,
    character: CharacterDetailsUi
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Character Image
        AsyncImage(
            model = character.imageUrl,
            contentDescription = "Character image of ${character.name}",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        // Character Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(label = "Status", value = character.status)
            InfoRow(label = "Species", value = character.species)
            if (character.type.isNotEmpty()) {
                InfoRow(label = "Type", value = character.type)
            }
            InfoRow(label = "Gender", value = character.gender)
            InfoRow(label = "Origin", value = character.origin)
            InfoRow(label = "Location", value = character.location)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Episodes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                character.episode.forEach { episode ->
                    EpisodeChip(episodeUrl = episode)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun EpisodeChip(
    episodeUrl: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = "Episode ${episodeUrl.substringAfterLast("/")}",
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailsScreenSuccessPreview() {
    Rick_and_MortyTheme {
        CharacterDetailsScreen(
            uiState = UiState.Content(
                character = CharacterDetailsUi(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "Genius",
                    gender = "Male",
                    origin = "Earth (C-137)",
                    episode = listOf(
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/3"
                    ),
                    location = "Citadel of Ricks",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                )
            ),
            navigateToListScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailsScreenLoadingPreview() {
    Rick_and_MortyTheme {
        CharacterDetailsScreen(
            uiState = UiState.Loading,
            navigateToListScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailsScreenErrorPreview() {
    Rick_and_MortyTheme {
        CharacterDetailsScreen(
            uiState = UiState.Error("Failed to load character"),
            navigateToListScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoRowPreview() {
    Rick_and_MortyTheme {
        InfoRow(
            label = "Status",
            value = "Alive"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EpisodeChipPreview() {
    Rick_and_MortyTheme {
        EpisodeChip(
            episodeUrl = "https://rickandmortyapi.com/api/episode/1"
        )
    }
}