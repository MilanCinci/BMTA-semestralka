package com.example.quizmaster.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmaster.model.QuizResult
import com.example.quizmaster.repository.QuizRepository

/**
 * ViewModel pro správu a poskytování historie výsledků kvízů
 */
class HistoryViewModel(context: Context) : ViewModel()
{
    private val repo = QuizRepository(context)
    val historyRecords = MutableLiveData<List<QuizResult>>()

    init
    {
        loadHistory()
    }

    /**
     * Metoda slouží k načtení historie kvízů z repozitáře
     */
    private fun loadHistory()
    {
        historyRecords.value = repo.loadHistory()
    }
}