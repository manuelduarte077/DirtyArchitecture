package dev.donmanuel.todoapp.di

import dev.donmanuel.todoapp.data.repository.TodoRepository
import dev.donmanuel.todoapp.data.local.Database
import dev.donmanuel.todoapp.data.local.RoomDB
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {
    single<RoomDB> { Database(get()).getDatabase() }

    singleOf(::TodoRepository)

}