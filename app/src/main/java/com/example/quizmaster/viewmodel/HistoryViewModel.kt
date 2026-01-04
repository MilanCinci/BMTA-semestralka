package com.example.quizmaster.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmaster.model.QuizResult
import com.example.quizmaster.repository.QuizRepository

/**
 * ViewModel pro správu a poskytování historie výsledků kvízů
 *
 *  @param context Kontext aplikace
 */
class HistoryViewModel(context: Context) : ViewModel()
{
    /** Repozitář pro načítání otázek a historie kvízů */
    private val repo = QuizRepository(context)

    /** LiveData obsahující seznam historie kvízů */
    val historyRecords = MutableLiveData<List<QuizResult>>()

    /** Při inicializace třídy se načte celá historie */
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