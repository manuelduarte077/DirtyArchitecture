package dev.donmanuel.booklibrary

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.booklibrary.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }