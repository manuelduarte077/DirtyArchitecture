package dev.donmanuel.weatherapp.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.weatherapp.presentation.features.home.events.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.weatherapp.R
import dev.donmanuel.weatherapp.core.DataResult
import dev.donmanuel.weatherapp.core.ResourceProvider
import dev.donmanuel.weatherapp.domain.model.CurrentCity
import dev.donmanuel.weatherapp.domain.model.FavoriteCity
import dev.donmanuel.weatherapp.domain.usecase.AddFavoriteCityUseCase
import dev.donmanuel.weatherapp.domain.usecase.DeleteFavoriteCityByIdUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherByCityUseCase
import dev.donmanuel.weatherapp.domain.usecase.GetWeatherSettingsUseCase
import dev.donmanuel.weatherapp.presentation.features.home.state.HomeState
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
class HomeViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase,
    private val getWeatherSettingsUseCase: GetWeatherSettingsUseCase,
    private val addFavoriteCityUseCase: AddFavoriteCityUseCase,
    private val deleteFavoriteCityByIdUseCase: DeleteFavoriteCityByIdUseCase
) : ViewModel() {

    // region States

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    // endregion States

    init {
        fetchItem()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.RetryFetch -> retryFetchItem()
            HomeEvent.CheckedFavorite -> handleFavorite()
        }
    }

    private fun fetchItem() = viewModelScope.launch {
        getWeatherSettingsUseCase.invoke().collectLatest { result ->
            when (result) {
                is DataResult.Success -> {
                    val (currentCity, measurementUnit) = result.data!!
                    loadWeather(currentCity, measurementUnit)
                }

                is DataResult.Failure -> _uiState.update {
                    it.copy(
                        weather = null,
                        currentCity = CurrentCity(),
                        measurementUnit = "",
                        isLoading = false,
                        errorMessage = resourceProvider.getString(R.string.unknown_error_getting_city)
                    )
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    private suspend fun loadWeather(currentCity: CurrentCity, measurementUnit: String) {
        getWeatherByCityUseCase.invoke(
            params = GetWeatherByCityUseCase.Params(
                query = currentCity.name,
                units = measurementUnit
            )
        ).collectLatest { result ->
            when (result) {
                is DataResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        weather = result.data,
                        currentCity = currentCity,
                        measurementUnit = measurementUnit,
                        isLoading = false,
                    )
                }

                is DataResult.Failure -> _uiState.update {
                    it.copy(
                        weather = null,
                        currentCity = CurrentCity(),
                        measurementUnit = "",
                        isLoading = false,
                        errorMessage = result.e?.message.toString()
                    )
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    private fun retryFetchItem() {
        _uiState.update {
            it.copy(errorMessage = "")
        }.apply {
            fetchItem()
        }
    }

    private fun handleFavorite() = viewModelScope.launch {
        if (_uiState.value.currentCity.isFavorite) {
            removeCityFromFavorites()
        } else {
            addCityToFavorites()
        }
    }

    private suspend fun addCityToFavorites() {
        val currentCityName = _uiState.value.currentCity.name
        val currentCityCountryName = _uiState.value.weather?.city?.country ?: ""

        addFavoriteCityUseCase.invoke(
            AddFavoriteCityUseCase.Params(
                FavoriteCity(
                    currentCityName,
                    currentCityCountryName
                )
            )
        ).collectLatest { result ->
            when (result) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            currentCity = it.currentCity.copy(isFavorite = true),
                            isLoading = false
                        )
                    }
                    _toastEvent.emit(resourceProvider.getString(R.string.city_added_to_favorites))
                }

                is DataResult.Failure -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _toastEvent.emit(resourceProvider.getString(R.string.error_adding_city_to_favorites))
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    private suspend fun removeCityFromFavorites() {
        val currentCityName = _uiState.value.currentCity.name

        deleteFavoriteCityByIdUseCase.invoke(DeleteFavoriteCityByIdUseCase.Params(currentCityName))
            .collectLatest { result ->
                when (result) {
                    is DataResult.Success -> {
                        _uiState.update {
                            it.copy(
                                currentCity = it.currentCity.copy(isFavorite = false),
                                isLoading = false
                            )
                        }
                        _toastEvent.emit(resourceProvider.getString(R.string.city_removed_from_favorites))
                    }

                    is DataResult.Failure -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _toastEvent.emit(resourceProvider.getString(R.string.error_removing_city_from_favorites))
                    }

                    is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                }
            }
    }
}
