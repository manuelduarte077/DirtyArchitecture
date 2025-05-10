package dev.donmanuel.todoapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.donmanuel.todoapp.data.model.TodoItem
import dev.donmanuel.todoapp.ui.composables.AddTodoDialog
import dev.donmanuel.todoapp.ui.composables.EditTodoDialog
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel = koinViewModel<SharedViewModel>()

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedTodo by remember { mutableStateOf<TodoItem?>(null) }

    val todoList by viewModel.getAllTodos().collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo App",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddDialog = true
                }, content = {
                    Icon(
                        imageVector = Icons.Filled.Add, contentDescription = "Add Todo"
                    )
                },
                containerColor = Color.Green
            )
        }

    ) { paddingValues ->

        Surface {
            Box(contentAlignment = Alignment.Center) {
                LazyColumn(modifier = modifier.padding(paddingValues)) {
                    itemsIndexed(items = todoList.orEmpty()) { index, item ->
                        ListItem(
                            modifier = modifier.fillMaxWidth().padding(2.dp),
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            leadingContent = {
                                Text(
                                    (index + 1).toString(),
                                )
                            },
                            headlineContent = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            trailingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "Edit Todo",
                                        tint = Color.Blue,
                                        modifier = modifier.padding(8.dp).clickable {
                                            selectedTodo = item
                                            showEditDialog = true
                                        }
                                    )

                                    Spacer(modifier = modifier.width(2.dp))

                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Edit Todo",
                                        tint = Color.Blue,
                                        modifier = modifier.padding(8.dp).clickable {
                                            viewModel.deleteTodo(item)
                                        }
                                    )
                                }

                            }
                        )
                    }
                }
            }

            if (showAddDialog) {
                AddTodoDialog(
                    onDismiss = { showAddDialog = false },
                    onConfirm = viewModel.insertTodo
                )
            }

            if (showEditDialog) {
                selectedTodo?.let {
                    EditTodoDialog(
                        todoItem = it,
                        onDismiss = { showEditDialog = false },
                        onConfirm = viewModel.updateTodo
                    )
                }
            }
        }
    }
}

