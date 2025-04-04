package dev.donmanuel.booklibrary

import android.app.Application
import dev.donmanuel.booklibrary.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApplication)
        }
    }
}