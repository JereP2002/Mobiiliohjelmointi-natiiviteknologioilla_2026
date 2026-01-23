package com.example.week_1_domin.domain

fun addTask(list: List<Task>, newtask: Task): List<Task> {
    return list + newtask
}

fun sortTasksByPriority(list: List<Task>): List<Task> {
    return list.sortedBy { it.priority }
}