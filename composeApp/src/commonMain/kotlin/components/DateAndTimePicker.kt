package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DateAndTimeField(
    value: LocalDateTime,
    onClick: (() -> Unit)? = null
) {
    val date = value.toLocalDate()
    val time = value.toLocalTime()
    var modifier = Modifier.fillMaxWidth()

    onClick?.let {
        modifier = modifier.clickable { onClick() }
    }

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = date.format(DateTimeFormatter.ISO_DATE),
            )
            Text(
                text = time.format(DateTimeFormatter.ofPattern("H:m:s")),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateAndTimeDialog(
    dismissRequest: () -> Unit,
    value: LocalDateTime,
    onChange: (LocalDateTime) -> Unit
) {
    val valueTime = value.toLocalTime()
    val valueDate = value.toLocalDate()
    val timeState = remember { TimePickerState(valueTime.hour, valueTime.minute, is24Hour = true) }
    val dateState = remember {
        DatePickerState(
            CalendarLocale.getDefault(),
            initialSelectedDateMillis = valueDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        )
    }

    val selectedDate = Instant.ofEpochMilli(dateState.selectedDateMillis!!).atZone(ZoneId.systemDefault()).toLocalDate()
    val selectedTime = LocalTime.of(timeState.hour, timeState.minute)
    val currentValue = LocalDateTime.of(selectedDate, selectedTime)

    Dialog(
        onDismissRequest = dismissRequest,
    ) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                DateAndTimeField(currentValue)

                Row{
                    TimePicker(
                        timeState,
                        layoutType = TimePickerLayoutType.Vertical
                    )
                    DatePicker(
                        dateState,
                        showModeToggle = false,
                    )
                }
                Button(
                    onClick = {
                        onChange(currentValue)
                        dismissRequest()
                    }
                ) {
                    Text("Ok")
                }
            }
        }

    }
}