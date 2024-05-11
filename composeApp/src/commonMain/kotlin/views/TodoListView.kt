package views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import model.TodoItemModel
import viewmodel.TodoListViewModel
import java.time.format.DateTimeFormatter

@Composable
fun ItemCard(
    todo: TodoItemModel,
    onClick: () -> Unit,
) {
    Card {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp).clickable { onClick() },
        ){
            var title = todo.created.toLocalDate().toString()
            Text(text = title, style = MaterialTheme.typography.bodySmall)

            Text(text = todo.description, style = MaterialTheme.typography.bodyLarge)

            if (todo.done) {
                Row {
                    Text(
                        text = "Done", color = Color.Green,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = todo.doneTime?.format(DateTimeFormatter.ofPattern("w/M/y H:m")) ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            } else {
                Text(
                    text = "Not Done",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Composable
fun TodoListView() {
    val model = remember { TodoListViewModel() }

    val todos by model.state.collectAsState()

    var showAddTask by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Text("My Todos", style = MaterialTheme.typography.titleMedium)
            }
        },
        floatingActionButton = {
            Button(
                onClick = {
                    showAddTask = true
                }
            ) {
                Icon(Icons.Default.Add, "Add Task Button")
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(16.dp, 16.dp + it.calculateTopPadding(), 16.dp, 16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(todos.size) {
                    val todo = todos[it]
                    ItemCard(
                        todo,
                        onClick = {
                            model.deleteTodo(todo)
                        }
                    )
                }
            }
            if (showAddTask) {
                Dialog(
                    onDismissRequest = {
                        showAddTask = false
                    }
                ) {
                    FormAddTask(
                        onAdd = {
                            model.addTodo(it)
                        }
                    )
                }
            }
        }
    }

}