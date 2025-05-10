package dev.donmanuel.todoapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.donmanuel.todoapp.data.model.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoDialog(
    modifier: Modifier = Modifier,
    todoItem: TodoItem,
    onDismiss: () -> Unit,
    onConfirm: (TodoItem) -> Unit,
) {

    var textTitle by remember { mutableStateOf(todoItem.title) }

    BasicAlertDialog(
        modifier = modifier.clip(RoundedCornerShape(20)),
        onDismissRequest = { onDismiss() },
    ) {
        Surface {
            Column(
                modifier = modifier.fillMaxWidth().padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = textTitle,
                    onValueChange = { newValue ->
                        textTitle = newValue.trimStart()
                    },
                )

                Spacer(modifier = modifier.height(2.dp))

                TextButton(
                    onClick = {
                        if (textTitle.isNotEmpty()) {
                            onConfirm(todoItem.copy(title = textTitle))
                            onDismiss()
                        }
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20),
                ) {
                    Text("Update Todo")
                }
            }
        }
    }
}