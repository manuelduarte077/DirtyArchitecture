package dev.donmanuel.todoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.donmanuel.todoapp.ui.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}