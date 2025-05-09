package dev.donmanuel.ktorexample

import android.app.Application
import dev.donmanuel.ktorexample.di.networkModule
import dev.donmanuel.ktorexample.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KtorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KtorApplication)
            modules(networkModule, sharedModule)
        }
    }
}