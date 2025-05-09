package dev.donmanuel.ktorexample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.donmanuel.ktorexample.util.NetworkResponse
import io.ktor.websocket.Frame
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<SharedViewModel>()

    val postState by viewModel.postState.collectAsStateWithLifecycle(initialValue = null)
    val todosList by viewModel.todosList.collectAsStateWithLifecycle(initialValue = null)

    var showPostStateAlert by remember { mutableStateOf(false) }


    LaunchedEffect(postState) {
        when (postState) {
            is NetworkResponse.Success,
            is NetworkResponse.Error -> {
                showPostStateAlert = true
            }

            is NetworkResponse.Loading, null -> {}
        }
    }

    Surface {
        if (showPostStateAlert) {
            AlertDialog(
                onDismissRequest = { showPostStateAlert = false },
                title = { Frame.Text(text = "Post State") },
                text = {
                    Text(postState?.data ?: postState?.message.orEmpty())
                },
                confirmButton = {
                    Button(onClick = { showPostStateAlert = false }) {
                        Text("OK")
                    }
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        viewModel.postTodo()
                    },
                    modifier = Modifier.size(150.dp),
                    shape = RoundedCornerShape(10)
                ) {
                    if (postState is NetworkResponse.Loading) {
                        Column {
                            Text("Posting")
                            LinearProgressIndicator(color = Color.White)
                        }
                    } else {
                        Text("Post Todo")
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        viewModel.getTodos()
                    },
                    modifier = Modifier.size(150.dp),
                    shape = RoundedCornerShape(10)
                ) {
                    Text("Get Todos")
                }
            }

            when (todosList) {
                is NetworkResponse.Error -> {
                    todosList?.message?.let { Text(it) }
                }

                is NetworkResponse.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Loading...")
                        CircularProgressIndicator(modifier = Modifier.size(200.dp))
                    }
                }

                is NetworkResponse.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        items(todosList?.data.orEmpty()) { item ->
                            ListItem(
                                modifier = Modifier.padding(bottom = 10.dp),
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                leadingContent = {
                                    Text(
                                        text = item.id.toString(),
                                    )
                                },
                                headlineContent = {
                                    Text(
                                        text = item.title,
                                    )
                                },
                            )
                        }
                    }
                }

                null -> {
                    Text("No Todos")
                }
            }

        }
    }

}