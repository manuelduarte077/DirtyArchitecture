package dev.donmanuel.weatherapp.presentation.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.weatherapp.R
import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.core.ResourceProvider
import dev.donmanuel.weatherapp.domain.usecase.SaveCurrentCityUseCase
import dev.donmanuel.weatherapp.presentation.features.search.events.SearchCityEvent
import dev.donmanuel.weatherapp.presentation.features.search.state.SearchCityState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val saveCurrentCityUseCase: SaveCurrentCityUseCase
) : ViewModel() {

    // region states
    private val _uiState = MutableStateFlow(SearchCityState())
    val uiState: StateFlow<SearchCityState> = _uiState.asStateFlow()

    private val _finishScreen = MutableSharedFlow<Unit>()
    val finishScreen: SharedFlow<Unit> = _finishScreen

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    // endregion states
    fun onEvent(event: SearchCityEvent) {
        when (event) {
            is SearchCityEvent.UpdateQuery -> _uiState.update { it.copy(query = event.query) }
            SearchCityEvent.ConfirmCity -> confirmCity()
        }
    }

    private fun confirmCity() = viewModelScope.launch {
        if (_uiState.value.query.isNotBlank()) {
            addCurrentCity()
        } else {
            _toastEvent.emit(resourceProvider.getString(R.string.please_enter_city_name))
        }
    }

    private suspend fun addCurrentCity() {
        saveCurrentCityUseCase.invoke(
            SaveCurrentCityUseCase.Params(_uiState.value.query.trim())
        ).collectLatest { result ->
            when (result) {
                is DataResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _finishScreen.emit(Unit)
                }

                is DataResult.Failure -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _toastEvent.emit(resourceProvider.getString(R.string.error_unexpected_error_occurred))
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }
}
