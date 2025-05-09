package dev.donmanuel.ktorexample.di

import dev.donmanuel.ktorexample.data.repository.RemoteRepoImpl
import dev.donmanuel.ktorexample.data.repository.RemoteRepository
import org.koin.core.module.dsl.viewModelOf
import dev.donmanuel.ktorexample.ui.SharedViewModel
import org.koin.dsl.module

val sharedModule = module {
    single<RemoteRepository> {
        RemoteRepoImpl(get())
    }

    viewModelOf(::SharedViewModel)
}