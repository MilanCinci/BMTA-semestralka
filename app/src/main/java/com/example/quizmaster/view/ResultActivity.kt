package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.quizmaster.R
import com.example.quizmaster.viewmodel.ResultViewModel

/**
 * Aktivita pro zobrazení výsledků dokončeného kvízu
 */
class ResultActivity : AppCompatActivity()
{

    /** ViewModel pro výpočet úspěšnosti */
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        // Načtení dat z intentu
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        // Výpočet úspěšnosti skrze ViewModel
        val percentage = viewModel.calculatePercentage(score, total)

        val scoreTextView = findViewById<TextView>(R.id.resultScore)
        val ratioTextView = findViewById<TextView>(R.id.resultRatio)

        // Nastavení textů
        scoreTextView.text = "$percentage %"
        ratioTextView.text = "Dosáhli jste $score z $total bodů"

        // Nastavení barvy na základě logiky ve ViewModelu
        if (viewModel.isSuccess(percentage)) {
            scoreTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        }

        else
        {
            scoreTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        // Vrácení se zpátky na Menu aplikace (MainActivity)
        findViewById<Button>(R.id.btnFinishResult).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}