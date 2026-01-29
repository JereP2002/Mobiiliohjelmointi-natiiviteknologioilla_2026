package com.example.week_1_domin.view

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.week_1_domin.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onSave(task.copy(title = title))
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDelete(task)
                onDismiss()
            }) {
                Text("Delete")
            }
        },
        title = { Text("Edit Task") },
        text = {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
        }
    )
}
