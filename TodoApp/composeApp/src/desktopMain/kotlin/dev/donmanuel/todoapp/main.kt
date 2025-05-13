package dev.donmanuel.todoapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.todoapp.di.desktopDatabaseModule
import dev.donmanuel.todoapp.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        modules(sharedModule, desktopDatabaseModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Todo App",
    ) {
        App()
    }
}