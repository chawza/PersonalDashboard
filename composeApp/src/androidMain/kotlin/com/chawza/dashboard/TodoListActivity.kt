package com.chawza.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chawza.dashboard.ui.theme.PersonalDashboardTheme
import views.TodoListView

class TodoListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalDashboardTheme {
                TodoListView(
                    backToDashboard = {
                        this@TodoListActivity.finish()
                    }
                )
            }
        }
    }
}
