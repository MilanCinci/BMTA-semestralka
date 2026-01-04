package com.example.quizmaster.model

/**
 * Třída pro reprezentaci a uchování dat výsledku kvízu
 */
data class QuizResult(
    /** Datum, kdy byl kvíz spuštěn */
    val date: String,

    /** Kategorie vybraného kvízu */
    val category: String,

    /** Konečné skóre uživatele z daného kvízu */
    val score: Int,

    /** Celkový počet otázek v kvízu */
    val total: Int
)