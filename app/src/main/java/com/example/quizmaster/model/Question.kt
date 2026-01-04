package com.example.quizmaster.model

import java.io.Serializable

/**
 * Třída pro reprezentaci a uchování dat otázky
 */
data class Question(
    /** Název kategorie */
    val category: String,

    /** Otázka */
    val question: String,

    /** Seznam s možnostmi odpovědí */
    val options: List<String>,

    /** Správný index odpovědi v možnostech */
    val correctIndex: Int
) : Serializable
