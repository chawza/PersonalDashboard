package com.chawza.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import MainDashboardView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainDashboardView()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}