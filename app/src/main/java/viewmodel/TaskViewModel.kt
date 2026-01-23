package com.example.week_1_domin.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week_1_domin.domain.Task

class TaskViewModel : ViewModel() {
    var task by mutableStateOf<List<Task>>(emptyList())
        private set

    init {
        task = listOf(
            Task(id = 1, title = "Compose UI", description = "Build first screen", priority = 1, dueDate = "2026-1-13", done = false),
            Task(id = 2, title = "ViewModel", description = "Add state management", priority = 2, dueDate = "2026-1-13", done = false),
            Task(id = 3, title = "Demo video", description = "Record Youtube demo", priority = 3, dueDate = "2026-1-13", done = false)
        )
    }

    fun addTask(newTask: Task) {
        task = task + newTask
    }

    fun toggleDone(id: Int) {
        task = task.map {
            if (id == it.id) it.copy(done = !it.done) else it
        }
    }
}