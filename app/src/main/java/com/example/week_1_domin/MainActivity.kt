package com.example.week_1_domin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.week_1_domin.ui.theme.Week_1_DominTheme
import com.example.week_1_domin.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week_1_DominTheme {
                HomeScreen()
            }
        }
    }
}
