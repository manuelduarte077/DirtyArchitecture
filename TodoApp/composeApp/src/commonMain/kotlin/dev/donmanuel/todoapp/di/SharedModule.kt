package dev.donmanuel.todoapp.di

import dev.donmanuel.todoapp.data.repository.TodoRepository
import dev.donmanuel.todoapp.data.local.Database
import dev.donmanuel.todoapp.data.local.RoomDB
import dev.donmanuel.todoapp.domain.usecase.DeleteTodoUseCase
import dev.donmanuel.todoapp.domain.usecase.GetAllTodosUseCase
import dev.donmanuel.todoapp.domain.usecase.InsertTodoUseCase
import dev.donmanuel.todoapp.domain.usecase.UpdateTodoUseCase
import dev.donmanuel.todoapp.ui.SharedViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {
    // Database
    single<RoomDB> { Database(get()).getDatabase() }

    // Repositories
    singleOf(::TodoRepository)

    // Use cases
    singleOf(::InsertTodoUseCase)
    singleOf(::GetAllTodosUseCase)
    singleOf(::DeleteTodoUseCase)
    singleOf(::UpdateTodoUseCase)

    // View Model
    singleOf(::SharedViewModel)

}