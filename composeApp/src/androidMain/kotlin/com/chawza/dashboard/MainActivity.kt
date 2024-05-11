package com.chawza.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import views.MainDashboardView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import App
import android.content.Intent
import com.chawza.dashboard.ui.theme.PersonalDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PersonalDashboardTheme {
                MainDashboardView(
                    navigateToTodoList = {
                        startActivity(
                            Intent(this@MainActivity, TodoListActivity::class.java)
                        )
                    }
                )
            }
        }
    }
}
