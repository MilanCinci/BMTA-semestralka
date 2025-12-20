package com.example.quizmaster.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.model.Question

/**
 * Adaptér pro RecyclerView, který slouží k zobrazení přehledu výsledků kvízu
 *
 * @property questions Seznam otázek, které byly v kvízu položeny
 * @property userAnswers Pole indexů odpovědí, které uživatel zvolil pro každou otázku
 */
class ReviewAdapter(
    private val questions: List<Question>,
    private val userAnswers: IntArray
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>()
{

    /**
     * ViewHolder pro jednotlivé položky seznamu výsledků
     */
    // V ReviewAdapter.kt uvnitř třídy ViewHolder
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.findViewById(R.id.textReviewQuestion)
        val userAns: TextView = view.findViewById(R.id.textUserAnswer)
        val correctAns: TextView = view.findViewById(R.id.textCorrectAnswer)
    }

    /**
     * Metoda slouží k vytvoření nového ViewHolder nafouknutím XML Layoutu
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    /**
     * Metoda slouží k propojení dat konkrétní otázky s prvky ve ViewHolderu
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val q = questions[position]
        val uAnsIndex = userAnswers[position]

        // Nastavení textu otázky
        holder.question.text = q.question

        // Získání textu odpovědi uživatele (ošetření případu, kdy nebyla vybrána žádná možnost)
        val uAnswerText = if (uAnsIndex != -1)
        {
            q.options[uAnsIndex]
        }

        else
        {
            "Nezodpovězeno"
        }

        holder.userAns.text = "Vaše odpověď: $uAnswerText"

        // Logika vyhodnocení správnosti a barevného zvýraznění
        if (uAnsIndex == q.correctIndex)
        {
            // Správná odpověď - zelená barva
            holder.userAns.setTextColor(Color.parseColor("#4CAF50"))
            holder.correctAns.visibility = View.GONE
        }

        else
        {
            // Špatná odpověď - červená barva a zobrazení správné odpovědi
            holder.userAns.setTextColor(Color.RED)
            holder.correctAns.text = "Správně: ${q.options[q.correctIndex]}"
            holder.correctAns.visibility = View.VISIBLE
        }
    }

    /**
     * Metoda slouží k vrácení celkového počtu otázek v seznamu
     */
    override fun getItemCount() = questions.size
}