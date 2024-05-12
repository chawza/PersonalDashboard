package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.DateAndTimeDialog
import components.DateAndTimeField
import model.TodoItemModel
import java.time.LocalDateTime

@Composable
fun FormAddTask(onAdd: (TodoItemModel) -> Unit, dismissRequest: () -> Unit) {
    var description by remember { mutableStateOf("") }
    var isDone by remember { mutableStateOf(false) }
    var scheduleTime by remember { mutableStateOf(LocalDateTime.now()) }

    var showScheduleTimeDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Add Todo", style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(
            description,
            onValueChange = { description = it},
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(isDone, onCheckedChange = { isDone = it })
            Text("Is Done")
        }
        DateAndTimeField(
            value = scheduleTime,
            onClick = {
                showScheduleTimeDialog = true
            }
        )

        if (showScheduleTimeDialog) {
            DateAndTimeDialog(
                dismissRequest = {
                    showScheduleTimeDialog = false
                },
                value = scheduleTime,
                onChange = { scheduleTime = it }
            )
        }
        Button(
            onClick = {
                if (
                    description.isEmpty()
                )
                    return@Button
                onAdd(
                    TodoItemModel(
                        description,
                        LocalDateTime.now(),
                        isDone,
                        if (isDone) LocalDateTime.now() else null
                    )
                )
                description = ""
                isDone = false
                dismissRequest()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }
    }
}
