package dev.donmanuel.kotlinandroidtemplate.presentation.state

import dev.donmanuel.kotlinandroidtemplate.domain.model.Planet

sealed class PlanetsState {
    data object Loading : PlanetsState()
    data class Success(val planets: List<Planet>) : PlanetsState()
    data class Error(val errorMessage: String) : PlanetsState()
}