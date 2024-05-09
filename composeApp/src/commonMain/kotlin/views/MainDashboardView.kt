package views

import DAYS
import MONTHS
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.util.Calendar
import java.util.TimeZone

@Composable
fun MainDashboardView() {
    val lang = "en"
    val userName = "Nabeel"
    
    Box(modifier = Modifier.padding(16.dp)) {
        // Main Content
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                Text("Hello,", style = MaterialTheme.typography.titleSmall)
                Text(userName, style = MaterialTheme.typography.headlineLarge)
            }
        }

        // Top Left: Date and Time
        val now by remember { mutableStateOf(Calendar.getInstance(TimeZone.getDefault())) }
        val day = DAYS[lang]!![now.get(Calendar.DAY_OF_WEEK)]
        val month = MONTHS[lang]!![now.get(Calendar.MONTH)]

        val currentDate = "$day, ${now.get(Calendar.DATE)} $month ${now.get(Calendar.YEAR)}"
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Text(text = currentDate)
        }
        // Top Right:
        // Bottom Right:
        // Bottom Left:
    }
}

@Preview
@Composable
fun DashboardPreview() {
    MaterialTheme {
        MainDashboardView()
    }
}