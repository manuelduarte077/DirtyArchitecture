package dev.donmanuel.characters_list.screen

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
import dev.donmanuel.characters_list.mapper.toCharacterUi
import dev.donmanuel.characters_list.model.CharacterUi
import dev.donmanuel.domain.usecases.GetCharactersUseCase
import javax.inject.Inject

internal sealed interface UiEvent {
    data object Init : UiEvent
    data object LoadNextPage : UiEvent
}

internal sealed interface UiState {
    data class Content(val characters: List<CharacterUi>) : UiState
    data object Loading : UiState
    data class Error(val error: String) : UiState
}

@HiltViewModel
internal class CharacterListScreenViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
        .onSubscription { handleEvent(UiEvent.Init) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading
        )

    private var currentPage = 1
    private var isLoading = false

    fun handleEvent(event: UiEvent) {
        when (event) {
            UiEvent.Init -> loadNextPage(currentPage)
            UiEvent.LoadNextPage -> loadNextPage(currentPage)
        }
    }

    private fun loadNextPage(page: Int) {
        if (isLoading) return

        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase(page = page)
                .onSuccess { characters ->
                    _uiState.update { currentState ->
                        val currentCharacters = if (currentState is UiState.Content) {
                            currentState.characters
                        } else {
                            emptyList()
                        }

                        UiState.Content(
                            characters = currentCharacters + characters.map { it.toCharacterUi() }
                        )
                    }
                    currentPage++
                }
                .onFailure { exception ->
                    _uiState.update {
                        UiState.Error(exception.message.toString())
                    }
                }
                .also {
                    isLoading = false
                }
        }
    }
}