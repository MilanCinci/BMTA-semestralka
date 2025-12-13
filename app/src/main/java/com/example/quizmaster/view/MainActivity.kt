package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.view.CategoryAdapter
import com.example.quizmaster.viewmodel.CategoryViewModel

/**
 * Hlavní aktivita aplikace. Zobrazuje seznam kategorií kvízu pomocí RecyclerView
 * a umožňuje přechod do QuizActivity po výběru kategorie
 */
class MainActivity : AppCompatActivity()
{
    /** ViewModel poskytující seznam dostupných kategorií. */
    private lateinit var viewModel: CategoryViewModel

    /**
     * Metoda slouží k volaní při vytvoření aktivity
     *
     * @param savedInstanceState Uložený stav aktivity
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = CategoryViewModel(this)

        // Nastavení RecyclerView pro zobrazení kategorií
        val recycler = findViewById<RecyclerView>(R.id.recyclerCategories)
        recycler.layoutManager = LinearLayoutManager(this)

        // Naslouchání pro seznam kategorií a aktualizace adapteru
        viewModel.categories.observe(this)
        { list ->
            recycler.adapter = CategoryAdapter(list)
            { category ->
                // Po kliknutí na kategorii se spustí QuizActivity
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("category", category)
                startActivity(intent)
            }
        }
    }
}