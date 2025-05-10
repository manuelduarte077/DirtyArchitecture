package dev.donmanuel.todoapp.domain.usecase

import dev.donmanuel.todoapp.data.model.TodoItem
import dev.donmanuel.todoapp.data.repository.TodoRepository

class DeleteTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todoItem: TodoItem) {
        todoRepository.deleteTodo(todoItem = todoItem)
    }
}