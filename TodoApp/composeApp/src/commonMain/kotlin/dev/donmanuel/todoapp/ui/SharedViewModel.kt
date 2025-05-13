package dev.donmanuel.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.todoapp.data.model.TodoItem
import dev.donmanuel.todoapp.domain.usecase.DeleteTodoUseCase
import dev.donmanuel.todoapp.domain.usecase.GetAllTodosUseCase
import dev.donmanuel.todoapp.domain.usecase.InsertTodoUseCase
import dev.donmanuel.todoapp.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase
) : ViewModel() {

    val insertTodo = fun(todoItem: TodoItem) {
        viewModelScope.launch { insertTodoUseCase(todoItem) }
    }

    val updateTodo = fun(todoItem: TodoItem) {
        viewModelScope.launch { updateTodoUseCase(todoItem) }
    }

    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch { deleteTodoUseCase(todoItem) }
    }

    fun getAllTodos(): Flow<List<TodoItem>?> {
        return getAllTodosUseCase()
    }
}