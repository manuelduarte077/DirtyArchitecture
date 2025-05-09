package dev.donmanuel.ktorexample.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(networkModule, sharedModule)
    }
}