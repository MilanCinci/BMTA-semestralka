package com.example.quizmaster.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.model.QuizResult

/**
 * RecyclerView adapter pro zobrazení historie výsledků kvízů.
 *
 * @param history Seznam výsledků kvízů
 */
class HistoryAdapter(private val history: List<QuizResult>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    /**
     * Vnitřní třída ViewHolder reprezentující jednu položku historie kvízu
     *
     * @param view Root view položky historie
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /** TextView zobrazující informace o výsledku kvízu */
        val text: TextView = view.findViewById(R.id.historyText)
    }

    /**
     * Metoda slouží k vytvoření nového ViewHolder pro položku historie
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    /**
     * Metoda slouží k navázání dat historie na ViewHolder pro danou pozici
     *
     * @param holder ViewHolder položky
     * @param position Index pozice položky v seznamu historie
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val h = history[position]
        holder.text.text = "${h.date} | ${h.category} | ${h.score}/${h.total}"
    }

    /**
     * Metoda slouží k vrácení počtu položek v historii kvízů
     */
    override fun getItemCount(): Int = history.size
}