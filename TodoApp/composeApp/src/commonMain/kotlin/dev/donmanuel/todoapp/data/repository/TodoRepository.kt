package dev.donmanuel.todoapp.data.repository

import dev.donmanuel.todoapp.data.local.RoomDB
import dev.donmanuel.todoapp.data.model.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val database: RoomDB
) {
    private val dispatcher = Dispatchers.IO

    suspend fun insertTodo(todoItem: TodoItem) {
        with(dispatcher) {
            database.todoDao().insertTodo(todoItem)
        }
    }

    suspend fun updateTodo(todoItem: TodoItem) {
        with(dispatcher) {
            database.todoDao().updateTodo(todoItem)
        }
    }

    suspend fun deleteTodo(todoItem: TodoItem) {
        with(dispatcher) {
            database.todoDao().deleteTodo(todoItem)
        }
    }

    fun getAllTodos(): Flow<List<TodoItem>?> {
        return database.todoDao().getAllTodos()
    }
}