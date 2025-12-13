package com.example.quizmaster.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.repository.QuizRepository

/**
 * Aktivita zobrazující historii odehraných kvízů
 */
class HistoryActivity : AppCompatActivity()
{
    /**
     * Metoda slouží k inicializaci RecyclerView a načte historii kvízů
     *
     * @param savedInstanceState Uložený stav aktivity
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // RecyclerView pro zobrazení historie výsledků
        val recycler = findViewById<RecyclerView>(R.id.recyclerHistory)
        recycler.layoutManager = LinearLayoutManager(this)

        // Repozitář pro práci s historií kvízů
        val repo = QuizRepository(this)
        val history = repo.loadHistory()

        // Nastavení adapteru pro zobrazení historie
        recycler.adapter = HistoryAdapter(history)
    }
}