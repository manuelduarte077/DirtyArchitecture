package dev.donmanuel.ktorexample.data.repository

import dev.donmanuel.ktorexample.data.remote.model.TodoItem
import dev.donmanuel.ktorexample.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun postTodo(): Flow<NetworkResponse<String>>
    fun getTodo(): Flow<NetworkResponse<List<TodoItem>>>
}