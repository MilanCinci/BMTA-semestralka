package com.example.quizmaster.model

data class Question(
    val category: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)
