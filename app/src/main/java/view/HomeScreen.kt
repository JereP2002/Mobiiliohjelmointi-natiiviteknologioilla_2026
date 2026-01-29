package com.example.week_1_domin.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week_1_domin.model.Task
import com.example.week_1_domin.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    var title by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("New task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (title.isNotBlank()) {
                viewModel.addTask(title)
                title = ""
            }
        }) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedTask = task }
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(task.title)
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )
                }
            }
        }
    }

    selectedTask?.let {
        DetailDialog(
            task = it,
            onDismiss = { selectedTask = null },
            onSave = { viewModel.updateTask(it) },
            onDelete = {
                viewModel.removeTask(it)
                selectedTask = null
            }
        )
    }
}
