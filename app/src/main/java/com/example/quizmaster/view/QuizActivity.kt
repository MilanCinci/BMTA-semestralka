package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizmaster.R
import com.example.quizmaster.viewmodel.QuizViewModel

/**
 * Aktivita zodpovědná za průběh kvízu. Zobrazuje otázky a odpovědi pro vybranou kategorii,
 * zpracovává odpovědi uživatele a je zodpovědná za správu výsledků kvízu
 */
class QuizActivity : AppCompatActivity() {

    /** ViewModel obsahující logiku kvízu */
    private lateinit var viewModel: QuizViewModel

    /**
     * Metoda volaná při vytvoření aktivity.
     * Inicializuje ViewModel, UI prvky a obsluhu kliknutí
     *
     * @param savedInstanceState Uložený stav aktivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Získání kategorie z intentu (pokud chybí, aktivita se ukončí)
        val category = intent.getStringExtra("category") ?: return

        // Inicializace ViewModelu pro danou kategorii
        viewModel = QuizViewModel(this, category)

        // TextView pro zobrazení průběhu
        val questionCounter = findViewById<TextView>(R.id.questionCounter)

        // TextView pro zobrazení textu otázky
        val questionText = findViewById<TextView>(R.id.questionText)

        // Seznam tlačítek pro odpovědi
        val buttons = listOf<Button>(
            findViewById(R.id.answerA),
            findViewById(R.id.answerB),
            findViewById(R.id.answerC),
            findViewById(R.id.answerD)
        )

        /**
         * Metoda slouží k zobrazení aktuální otázky a nastavení textu odpovědí
         */
        fun showQuestion() {
            val q = viewModel.getCurrentQuestion()
            questionText.text = q.question

            // Aktualizace počítadla (např. 3 / 15)
            val currentNumber = (viewModel.currentIndex.value ?: 0) + 1
            questionCounter.text = "$currentNumber / ${viewModel.totalQuestions}"

            buttons.forEachIndexed { index, button ->
                if (index < q.options.size)
                {
                    button.text = q.options[index]
                    button.visibility = View.VISIBLE
                }

                else
                {
                    button.visibility = View.GONE
                }
            }
        }

        // Nastavení listeneru kliknutí pro jednotlivá tlačítka
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                // Vyhodnocení zvolené odpovědi
                viewModel.answerQuestion(index)

                // Kvíz pokračuje, pokud je aktuální index stále menší než celkový počet otázek
                if (viewModel.currentIndex.value!! < viewModel.totalQuestions)
                {
                    showQuestion()
                }

                else
                {
                    // Konec kvízu – uložení výsledku a přechod na vyhodnocení kvízu
                    viewModel.saveResult()
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("score", viewModel.correctCount.value ?: 0)
                    intent.putExtra("total", viewModel.totalQuestions)

                    startActivity(intent)
                    finish()
                }
            }
        }

        // Zobrazení první otázky po spuštění aktivity
        showQuestion()
    }
}
