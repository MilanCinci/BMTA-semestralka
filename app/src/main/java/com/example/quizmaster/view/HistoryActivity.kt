package com.example.quizmaster.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.viewmodel.HistoryViewModel

/**
 * Aktivita zobrazující historii odehraných kvízů pomocí RecyclerView a ViewModelu
 */
class HistoryActivity : AppCompatActivity()
{
    /** ViewModel pro načítání historie */
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Inicializace ViewModelu
        viewModel = HistoryViewModel(applicationContext)

        val recycler = findViewById<RecyclerView>(R.id.recyclerHistory)
        recycler.layoutManager = LinearLayoutManager(this)

        // Pozorování dat z ViewModelu
        viewModel.historyRecords.observe(this) { history ->
            recycler.adapter = HistoryAdapter(history)
        }
    }
}