package dev.donmanuel.ktorexample.data.repository

import dev.donmanuel.ktorexample.data.remote.model.TodoItem
import dev.donmanuel.ktorexample.util.NetworkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareGet
import io.ktor.client.request.preparePost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay

class RemoteRepoImpl(
    private val ktorClient: HttpClient,
) : RemoteRepository {

    override fun postTodo(): Flow<NetworkResponse<String>> = flow {

        try {
            emit(NetworkResponse.Loading())
            val request = ktorClient
                .preparePost("https://jsonplaceholder.typicode.com/todos") {
                    header("Content-Type", "application/json")
                    parameter("userId", 1)
                    parameter("id", 1)
                    parameter("title", "Todo Title")
                    parameter("completed", false)
                }
            delay(2000)
            request.execute { response ->
                emit(NetworkResponse.Success(response.toString()))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.toString()))
        }

    }

    override fun getTodo(): Flow<NetworkResponse<List<TodoItem>>> = flow {
        try {
            val request = ktorClient
                .prepareGet("https://jsonplaceholder.typicode.com/todos")

            delay(2000)
            request.execute { response ->
                val responseBody = response.body<List<TodoItem>>()
                emit(NetworkResponse.Success(data = responseBody))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.toString()))
        }
    }
}
