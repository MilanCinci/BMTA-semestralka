package com.example.quizmaster.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.quizmaster.R
import com.example.quizmaster.viewmodel.MainViewModel

/**
 * Hlavní aktivita aplikace. Zobrazuje menu a umožňuje navigaci do kategorií,
 * historie a přepínání tmavého režimu
 */
class MainActivity : AppCompatActivity() {

    /** ViewModel pro správu logiky hlavního menu */
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // Inicializace ViewModelu
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val btnCategories = findViewById<Button>(R.id.btnCategories)
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        val btnDarkModeToggle = findViewById<ImageButton>(R.id.btnDarkModeToggle)

        // Tlačítko pro Kategorie: Spustí aktivitu CategoryListActivity
        btnCategories.setOnClickListener {
            startActivity(Intent(this, CategoryListActivity::class.java))
        }

        // Tlačítko pro Historii: Spustí aktivitu HistoryActivity
        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Tlačítko pro přepnutí tmavého/světlého režimu
        btnDarkModeToggle.setOnClickListener {
            // Zjištění aktuálního stavu systému
            val currentMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            val newMode = viewModel.getNextNightMode(currentMode)

            // Přepnutí režimu a vynucení restartu aktivity
            AppCompatDelegate.setDefaultNightMode(newMode)

            // Pro jistotu použijeme intent pro čistý restart celé aktivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

            // Odstraní bliknutí při přechodu
            overridePendingTransition(0, 0)
        }
    }
}