package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.quizmaster.R

/**
 * Hlavní aktivita aplikace. Zobrazuje seznam kategorií kvízu pomocí RecyclerView
 * a umožňuje přechod do QuizActivity po výběru kategorie
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Předpokládá se, že layout activity_main.xml byl aktualizován (viz níže)
        setContentView(R.layout.activity_main)

        // Skryje horní lištu, protože nadpis "QuizMaster" je v layoutu
        supportActionBar?.hide()

        // 1. Odkazy na UI prvky
        val btnCategories = findViewById<Button>(R.id.btnCategories)
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        val btnDarkModeToggle = findViewById<ImageButton>(R.id.btnDarkModeToggle)

        // 2. Nastavení Listenerů

        // Tlačítko pro Kategorie: Spustí aktivitu se seznamem kategorií
        btnCategories.setOnClickListener {
            // POZNÁMKA: CategoryListActivity musí být vytvořena uživatelem.
            startActivity(Intent(this, CategoryListActivity::class.java))
        }

        // Tlačítko pro Historii: Spustí existující HistoryActivity
        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Tlačítko pro přepnutí tmavého/světlého režimu (Moon icon)
        btnDarkModeToggle.setOnClickListener {
            toggleDarkMode()
        }
    }

    /**
     * Přepne mezi světlým a tmavým režimem pomocí AppCompatDelegate.
     */
    private fun toggleDarkMode() {
        // Získá aktuální noční režim konfigurace
        val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        // Zjistí, zda je aktuálně aktivní tmavý režim (UI_MODE_NIGHT_YES)
        val newMode = if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO // Přepnout na světlý režim
        } else {
            AppCompatDelegate.MODE_NIGHT_YES // Přepnout na tmavý režim
        }

        // Aplikuje nový režim. Aplikace se automaticky restartuje.
        AppCompatDelegate.setDefaultNightMode(newMode)

        // Doporučení: Pro zachování režimu po restartu telefonu by se měla hodnota newMode uložit do SharedPreferences.
    }
}