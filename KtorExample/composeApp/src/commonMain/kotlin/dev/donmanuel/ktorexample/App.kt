package dev.donmanuel.ktorexample

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.donmanuel.ktorexample.ui.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}