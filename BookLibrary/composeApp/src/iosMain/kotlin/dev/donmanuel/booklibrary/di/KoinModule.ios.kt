package dev.donmanuel.booklibrary.di

import dev.donmanuel.booklibrary.database.getDatabaseBuilder
import org.koin.dsl.module

actual val targetModule = module {
    single { getDatabaseBuilder() }
}
