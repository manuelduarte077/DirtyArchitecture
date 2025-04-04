package dev.donmanuel.booklibrary.di

import dev.donmanuel.booklibrary.data.getRoomDatabase
import dev.donmanuel.booklibrary.presentation.screen.details.DetailsViewModel
import dev.donmanuel.booklibrary.presentation.screen.home.HomeViewModel
import dev.donmanuel.booklibrary.presentation.screen.manage.ManageViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val targetModule: Module

val sharedModule = module {
    single { getRoomDatabase(get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ManageViewModel)
    viewModelOf(::DetailsViewModel)
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(targetModule, sharedModule)
    }
}