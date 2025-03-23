package dev.donmanuel.weatherapp

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.weatherapp.presentation.navigation.AppNavigation
import dev.donmanuel.weatherapp.ui.theme.WeatherAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWhiteIconsOnStatusBar(window)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavigation()
    }
}

private fun setWhiteIconsOnStatusBar(window: Window) {
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
}