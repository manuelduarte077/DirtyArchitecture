package dev.donmanuel.todoapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.todoapp.di.desktopDatabaseModule
import dev.donmanuel.todoapp.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        modules(desktopDatabaseModule, sharedModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "TodoApp",
    ) {
        App()
    }
}