package dev.donmanuel.ktorexample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.ktorexample.di.networkModule
import dev.donmanuel.ktorexample.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        modules(networkModule, sharedModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "KtorExample",
    ) {
        App()
    }
}