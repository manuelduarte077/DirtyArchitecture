package dev.donmanuel.todoapp.domain.usecase

import dev.donmanuel.todoapp.data.model.TodoItem
import dev.donmanuel.todoapp.data.repository.TodoRepository

class InsertTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todoItem: TodoItem) {
        todoRepository.insertTodo(todoItem = todoItem)
    }
}