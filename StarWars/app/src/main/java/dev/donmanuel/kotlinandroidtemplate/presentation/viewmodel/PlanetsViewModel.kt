package dev.donmanuel.kotlinandroidtemplate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.kotlinandroidtemplate.domain.usecase.GetNextPageUseCase
import dev.donmanuel.kotlinandroidtemplate.domain.usecase.GetPlanetsUseCase
import dev.donmanuel.kotlinandroidtemplate.presentation.event.PlanetEvent
import dev.donmanuel.kotlinandroidtemplate.presentation.state.PlanetsState
import dev.donmanuel.kotlinandroidtemplate.utils.NoNetworkException
import dev.donmanuel.kotlinandroidtemplate.utils.RemoteDataSourceException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel responsible for managing the state of planets in the app
@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase,
    private val getNextPageUseCase: GetNextPageUseCase
) : ViewModel() {

    // Mutable state to hold the planets' UI state (e.g., loading, success, error)
    private val _planetsState = MutableStateFlow<PlanetsState>(PlanetsState.Loading)
    val planetsState = _planetsState.asStateFlow()

    // Mutable state to manage loading status of next page
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    // Holds the URL for the next page of results
    private var nextPageUrl: String? = null

    // CoroutineExceptionHandler to handle exceptions in coroutines
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    // Initialization: fetch the initial list of planets when the ViewModel is created
    init {
        fetchPlanets()
    }

    // This function listens for events (e.g., load more planets, fetch planets)
    fun planetEvent(event: PlanetEvent) {
        when (event) {
            PlanetEvent.LoadMorePlanets -> {
                loadMorePlanets()
            }

            PlanetEvent.FetchPlanets -> {
                fetchPlanets()
            }
        }
    }

    private fun fetchPlanets() {
        getPlanetsUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _planetsState.value = PlanetsState.Loading }
            .map { result ->
                result.getOrThrow().also { response ->
                    nextPageUrl = response.next
                }.results
            }
            .catch { error -> handleError(error) }
            .onEach { planets -> _planetsState.value = PlanetsState.Success(planets) }
            .launchIn(viewModelScope)
    }

    private fun loadMorePlanets() {
        if (_isLoadingMore.value || nextPageUrl == null) return

        viewModelScope.launch {
            getNextPageUseCase(nextPageUrl!!)
                .map { result ->
                    result.getOrThrow().also { response -> nextPageUrl = response.next }.results
                }
                .onStart { _isLoadingMore.value = true } // Set loading state before execution
                .onCompletion {
                    _isLoadingMore.value = false
                } // Reset loading state after execution
                .catch { error -> handleError(error) } // Handle errors
                .flowOn(Dispatchers.IO) // Ensure Flow runs on IO dispatcher
                .collect { newPlanets ->
                    _planetsState.update { currentState ->
                        val currentPlanets =
                            (currentState as? PlanetsState.Success)?.planets.orEmpty()
                        PlanetsState.Success(currentPlanets + newPlanets)
                    }
                }
        }
    }

    private fun handleError(error: Throwable) {
        _planetsState.update {
            when (error) {
                is NoNetworkException -> PlanetsState.Error(
                    error.message ?: "No network connection available"
                )

                is RemoteDataSourceException -> PlanetsState.Error(
                    error.message ?: "Unknown error"
                )

                else -> PlanetsState.Error(error.message ?: "")
            }
        }
    }
}