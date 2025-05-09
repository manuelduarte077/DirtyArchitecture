package dev.donmanuel.ktorexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.ktorexample.data.remote.model.TodoItem
import dev.donmanuel.ktorexample.data.repository.RemoteRepository
import dev.donmanuel.ktorexample.util.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel(
    private val repository: RemoteRepository
) : ViewModel() {
    private val _postState = MutableStateFlow<NetworkResponse<String>?>(null)
    val postState = _postState.asStateFlow()


    private val _todoList = MutableStateFlow<NetworkResponse<List<TodoItem>>?>(null)
    val todosList = _todoList.asStateFlow()

    val postTodo = fun() {
        viewModelScope.launch {
            repository.postTodo().collect { response ->
                _postState.update {
                    response
                }
            }
        }
    }

    val getTodos = fun() {
        viewModelScope.launch {
            repository.getTodo().collect { response ->
                _todoList.update {
                    response
                }
            }
        }
    }
}