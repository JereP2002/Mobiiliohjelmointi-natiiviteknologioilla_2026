package com.example.week_1_domin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week_1_domin.domain.Task
import com.example.week_1_domin.domain.mockTask
import com.example.week_1_domin.ui.theme.Week_1_DominTheme

class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        enableEdgeToEdge()
        setContent {
            Week_1_DominTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ParentComponent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.d(tag, "onResume")
    }
}

@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit
){
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = "Name") }
    )
}

@Composable
fun ParentComponent(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(mockTask.toMutableList()) }

    Column(modifier = modifier) {
        NameTextField(
            name = name,
            onNameChange = { name = it }
        )
        Text(text = "Tervehdys: $name")
        Spacer(modifier = Modifier.height(height = 16.dp))

        taskList.forEach { task ->
            Text(text = "${task.id} -Title: ${task.title}")
        }

        Button(
            onClick = {
                if (name.isNotBlank()) {
                    val newTask = Task(
                        id = (taskList.maxOfOrNull { it.id } ?: 0) + 1,
                        title = name,
                        description = "Description",
                        priority = 1,
                        dueDate = "2026-1-13",
                        done = false
                    )
                    taskList = (taskList + newTask).toMutableList()
                    name = ""
                }
            }
        ) {
            Text("Add Task")
        }
    }
}
