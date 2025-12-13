package com.example.quizmaster.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmaster.repository.QuizRepository

/**
 * ViewModel pro práci s kategoriemi kvízových otázek.
 * Načítá všechny dostupné otázky a poskytuje seznam kategorií
 *
 * @param context Kontext aplikace
 */
class CategoryViewModel(context: Context) : ViewModel()
{
    /** Repozitář pro načítání otázek a historie kvízů */
    private val repo = QuizRepository(context)

    /** LiveData obsahující seznam unikátních kategorií otázek */
    val categories = MutableLiveData<List<String>>()

    /** Při inicializace třídy se načtou všechny kategorie otázek */
    init
    {
        loadCategories()
    }

    /**
     * Metoda slouží k načtení všech otázek z repository a k aktualizování kategorií
     */
    private fun loadCategories()
    {
        val allQuestions = repo.loadQuestions()
        categories.value = allQuestions.map { it.category }.distinct()
    }
}