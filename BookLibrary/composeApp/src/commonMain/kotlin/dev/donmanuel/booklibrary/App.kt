package dev.donmanuel.booklibrary

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.booklibrary.navigation.SetupNavGraph

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        SetupNavGraph(navController)
    }
}