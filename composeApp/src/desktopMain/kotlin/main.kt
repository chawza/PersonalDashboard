import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import views.MainDashboardView
import views.TodoListView

enum class CurrentWindow {
    HOME, TODO
}
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PersonalDashboard",
    ) {
        var current by remember { mutableStateOf(CurrentWindow.HOME) }
        when (current) {
            CurrentWindow.HOME -> {
                MainDashboardView(
                    navigateToTodoList = {
                        current = CurrentWindow.TODO
                    }
                )
            }
            CurrentWindow.TODO -> {
                TodoListView(
                    backToDashboard = {
                        current = CurrentWindow.HOME
                    }
                )
            }
        }
    }
}