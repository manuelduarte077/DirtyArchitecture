package dev.donmanuel.todoapp

import android.app.Application
import dev.donmanuel.todoapp.di.androidDatabaseModule
import dev.donmanuel.todoapp.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TodoApplication)
            modules(androidDatabaseModule, sharedModule)
        }
    }
}