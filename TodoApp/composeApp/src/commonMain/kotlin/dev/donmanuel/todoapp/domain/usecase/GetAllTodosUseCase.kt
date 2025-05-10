package dev.donmanuel.todoapp.domain.usecase

import dev.donmanuel.todoapp.data.model.TodoItem
import dev.donmanuel.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodosUseCase(
    private val todoRepository: TodoRepository
) {
    operator fun invoke(): Flow<List<TodoItem>?> {
        return todoRepository.getAllTodos()
    }
}