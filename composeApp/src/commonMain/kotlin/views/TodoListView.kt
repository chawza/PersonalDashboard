package views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.asFlow
import model.TodoItemModel
import viewmodel.TodoListViewModel
import java.time.LocalDateTime

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
            todo.doneTime?.let {
                title += " - " + todo.doneTime!!.toLocalDate().toString()
            }
            Text(text = title)
            Text(text = todo.description)
            if (todo.done) {
                Text(text = "Done", color = Color.Green)
            }
        }
    }
}

@Composable
fun TodoListView() {
    val model = remember { TodoListViewModel() }

    val todos by model.state.collectAsState()

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Text("My Todos", style = MaterialTheme.typography.titleMedium)
            Row {
                Box(
                    modifier = Modifier.width(400.dp)
                ) {
                    FormAddTask(
                        onAdd = {
                            model.addTodo(it)
                        }
                    )
                }
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
            }
        }
    }
}