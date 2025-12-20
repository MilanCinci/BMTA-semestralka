package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.model.Question
import com.example.quizmaster.viewmodel.ResultViewModel

/**
 * Aktivita zodpovědná za zobrazení finálního vyhodnocení kvízu.
 * Zobrazuje procentuální úspěšnost, bodový zisk a detailní seznam
 * správných i špatných odpovědí uživatele.
 */
class ResultActivity : AppCompatActivity() {

    /** ViewModel pro logiku vyhodnocení úspěšnosti */
    private lateinit var viewModel: ResultViewModel

    /**
     * Inicializuje UI prvky, načte data z předchozí aktivity a nastaví seznam výsledků.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Inicializace ViewModelu
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        // Načtení základních dat o skóre z Intentu
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        // Načtení seznamu otázek a odpovědí uživatele pro detailní přehled
        // Nutné přetypování na ArrayList pro Serializable data
        val questions = intent.getSerializableExtra("questions_list") as? ArrayList<Question> ?: arrayListOf()
        val userAnswers = intent.getIntArrayExtra("user_answers") ?: intArrayOf()

        // Výpočet úspěšnosti v procentech skrze ViewModel
        val percentage = viewModel.calculatePercentage(score, total)

        // Propojení s UI prvky
        val scoreTextView = findViewById<TextView>(R.id.resultScore)
        val ratioTextView = findViewById<TextView>(R.id.resultRatio)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerReview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReviewAdapter(questions, userAnswers)

        // Nastavení detailního přehledu otázek pomocí RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReviewAdapter(questions, userAnswers)

        // Nastavení textového hodnocení
        scoreTextView.text = "$percentage %"
        ratioTextView.text = "Dosáhli jste $score z $total bodů"

        // Dynamická změna barvy procent podle úspěšnosti (zelená/červená)
        if (viewModel.isSuccess(percentage)) {
            scoreTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            scoreTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        // Tlačítko pro ukončení a návrat do hlavního menu
        findViewById<Button>(R.id.btnFinishResult).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Vyčištění historie aktivit, aby se uživatel nemohl vrátit tlačítkem zpět do hotového kvízu
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}