package com.example.week_1_domin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.week_1_domin.model.Task
import kotlin.collections.filterNot
import kotlin.collections.map

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(
        listOf(
            Task(1, "Compose UI", "Build UI", 1, "2026-01-13", false),
            Task(2, "ViewModel", "StateFlow", 2, "2026-01-13", false)
        )
    )

    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(title: String) {
        val newTask = Task(
            id = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = "Description",
            priority = 1,
            dueDate = "2026-01-13",
            done = false
        )
        _tasks.value = _tasks.value + newTask
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updated.id) updated else it
        }
    }

    fun removeTask(task: Task) {
        _tasks.value = _tasks.value.filterNot { it.id == task.id }
    }
}
