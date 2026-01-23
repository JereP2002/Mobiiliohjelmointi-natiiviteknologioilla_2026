package com.example.week_1_domin.domain

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val done: Boolean,
    val priority: Int
)



