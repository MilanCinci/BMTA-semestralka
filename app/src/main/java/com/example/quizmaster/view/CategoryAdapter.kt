package com.example.quizmaster.view

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.quizmaster.R

/**
 * RecyclerView adapter pro zobrazení seznamu kategorií kvízu
 *
 * @param categories Seznam názvů kategorií
 * @param onClick Lambda funkce volaná při kliknutí na kategorii
 */
class CategoryAdapter(
    private val categories: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    /**
     * Vnitřní třída ViewHolder reprezentující jednu položku seznamu kategorií
     *
     * @param textView TextView zobrazující název kategorie
     */
    inner class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    {
        /**
         * Metoda slouží k nastavení dat kategorie a obsluhu kliknutí
         *
         * @param category Název kategorie, která se má zobrazit
         */
        fun bind(category: String)
        {
            textView.text = category
            textView.setOnClickListener { onClick(category) }
        }
    }

    /**
     * Metoda slouží k vytvoření nového ViewHolder pro položku RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val tv = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false) as TextView
        return ViewHolder(tv)
    }

    /**
     * Metoda slouží k navázání dat na ViewHolder pro danou pozici
     *
     * @param holder ViewHolder položky
     * @param position Index pozice položky v seznamu
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(categories[position])
    }

    /**
     * Metoda slouží k vrácení počtu položek v seznamu kategorií
     */
    override fun getItemCount(): Int = categories.size
}