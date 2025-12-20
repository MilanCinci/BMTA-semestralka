package com.example.quizmaster.model

import java.io.Serializable

data class Question(
    val category: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int
) : Serializable
