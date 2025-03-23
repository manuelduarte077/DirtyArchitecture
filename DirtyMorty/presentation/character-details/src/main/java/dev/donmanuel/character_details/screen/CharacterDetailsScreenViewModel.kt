package dev.donmanuel.character_details.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import dev.donmanuel.character_details.mapper.toCharacterUi
import dev.donmanuel.character_details.model.CharacterDetailsUi
import dev.donmanuel.character_details.screen.navigation.CHARACTER_ID_ARG
import dev.donmanuel.domain.usecases.GetCharactersDetailsUseCase
import javax.inject.Inject

internal sealed interface UiEvent {
    data object Init : UiEvent
}

internal sealed interface UiState {
    data class Content(val character: CharacterDetailsUi) : UiState
    data object Loading : UiState
    data class Error(val error: String) : UiState
}

@HiltViewModel
internal class CharacterDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharactersDetailsUseCase: GetCharactersDetailsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
        .onSubscription { handleEvent(UiEvent.Init) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading
        )

    private val characterId: Long? = savedStateHandle[CHARACTER_ID_ARG]

    fun handleEvent(event: UiEvent) {
        when (event) {
            UiEvent.Init -> loadDetails()
        }
    }

    private fun loadDetails() {
        if (characterId == null) return

        viewModelScope.launch(Dispatchers.IO) {
            getCharactersDetailsUseCase(characterId)
                .onSuccess { character ->
                    _uiState.update {
                        UiState.Content(
                            character = character.toCharacterUi()
                        )
                    }
                }
                .onFailure { exception ->
                    UiState.Error(error = exception.message.toString())
                }
        }
    }
}