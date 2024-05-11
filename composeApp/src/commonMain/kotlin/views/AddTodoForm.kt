package views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import components.DateAndTimeDialog
import components.DateAndTimeField
import model.TodoItemModel
import java.time.LocalDateTime

@Composable
fun FormAddTask(onAdd: (TodoItemModel) -> Unit) {
    var description by remember { mutableStateOf("") }
    var isDone by remember { mutableStateOf(false) }
    var scheduleTime by remember { mutableStateOf(LocalDateTime.now()) }

    var showScheduleTimeDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
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
        DateAndTimeField(scheduleTime) {
            showScheduleTimeDialog = true
        }

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
                onAdd(
                    TodoItemModel(
                        description,
                        LocalDateTime.now(),
                        isDone,
                        LocalDateTime.now()
                    )
                )
                description = ""
                isDone = false
            }
        ) {
            Text("Add")
        }
    }
}
