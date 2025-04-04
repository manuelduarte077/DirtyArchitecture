package dev.donmanuel.booklibrary.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.donmanuel.booklibrary.presentation.components.ErrorView
import dev.donmanuel.booklibrary.presentation.components.LoadingView
import dev.donmanuel.booklibrary.presentation.screen.component.BookView
import dev.donmanuel.booklibrary.util.DisplayResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBookSelect: (Int) -> Unit,
    onCreateClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val viewModel = koinViewModel<HomeViewModel>()
    val books by viewModel.books
    val sortedByFavorite by viewModel.sortedByFavorite.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Library") },
                actions = {
                    IconButton(
                        onClick = {
                            if (books.isSuccess() && books.getSuccessData().size >= 2) {
                                viewModel.toggleSortByFavorite()
                                scope.launch {
                                    delay(100)
                                    listState.animateScrollToItem(0)
                                }
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.alpha(
                                if (sortedByFavorite) 1f else 0.38f
                            ),
                            imageVector = Icons.Default.Star,
                            contentDescription = "Sorting Icon",
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon"
                )
            }
        }
    ) {
        books.DisplayResult(
            onLoading = { LoadingView() },
            onError = { ErrorView(it) },
            onSuccess = { data ->
                if (data.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(all = 12.dp)
                            .padding(
                                top = it.calculateTopPadding(),
                                bottom = it.calculateBottomPadding()
                            ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = data,
                            key = { it.id }
                        ) {
                            BookView(
                                book = it,
                                onClick = { onBookSelect(it.id) }
                            )
                        }
                    }
                } else {
                    ErrorView()
                }
            }
        )
    }
}