package com.example.quizmaster.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmaster.repository.QuizRepository
import com.example.quizmaster.model.Question
import com.example.quizmaster.model.QuizResult
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel pro správu kvízů v rámci jedné kategorie

 *
 * @param context Kontext aplikace
 * @param category Kategorie otázek, se kterou bude kvíz pracovat
 */
class QuizViewModel(context: Context, private val category: String) : ViewModel()
{

    /** Repository pro načítání otázek a historie výsledků */
    private val repo = QuizRepository(context)

    /** Seznam otázek, který je vyfiltrován podle zvolené kategorie. */
    private val questions = repo.loadQuestions().filter { it.category == category }

    /** LiveData aktuálního indexu otázky */
    val currentIndex = MutableLiveData(0)

    /** Celkový počet otázek pro kontrolu v aktivitě */
    val totalQuestions: Int = questions.size

    /** LiveData počtu správných odpovědí */
    val correctCount = MutableLiveData(0)

    /**
     * Metoda slouží k získání aktuální otázky podle indexu
     *
     * @return Vrací aktuální otázku
     */
    fun getCurrentQuestion(): Question = questions[currentIndex.value!!]

    /**
     * Metoda slouží k vyhodnocování odpovědi uživatele a aktualizuje počet správných odpovědí.
     * Posune se na další otázku, pokud ještě existuje
     *
     * @param selectedIndex Index odpovědi, kterou uživatel zvolil
     */
    /**
     * Metoda slouží k vyhodnocování odpovědi uživatele a aktualizuje počet správných odpovědí.
     * Posune se na další otázku, pokud ještě existuje
     *
     * @param selectedIndex Index odpovědi, kterou uživatel zvolil
     */
    fun answerQuestion(selectedIndex: Int)
    {
        if (selectedIndex == getCurrentQuestion().correctIndex)
        {
            correctCount.value = correctCount.value!! + 1
        }

        if (currentIndex.value!! < questions.size)
        {
            currentIndex.value = currentIndex.value!! + 1
        }
    }

    /**
     * Metoda slouží k uložení výsledku aktuálního kvízu do historie
     */
    fun saveResult()
    {
        val history = repo.loadHistory()
        history.add(
            QuizResult(
                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date()),
                category = category,
                score = correctCount.value!!,
                total = questions.size
            )
        )
        repo.saveHistory(history)
    }
}
