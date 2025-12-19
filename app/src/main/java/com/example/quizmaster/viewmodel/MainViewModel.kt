package com.example.quizmaster.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel

/**
 * ViewModel pro hlavní obrazovku aplikace
 */
class MainViewModel : ViewModel()
{
    /**
     * Metoda slouží k určení, jaký režim má být nastaven na základě aktuálního stavu
     *
     * @param currentNightModeMask Aktuální maska režimu
     * @return Konstanta z AppCompatDelegate (MODE_NIGHT_NO nebo MODE_NIGHT_YES)
     */
    fun getNextNightMode(currentNightModeMask: Int): Int
    {
        return if (currentNightModeMask == android.content.res.Configuration.UI_MODE_NIGHT_YES)
        {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        else
        {
            AppCompatDelegate.MODE_NIGHT_YES
        }
    }
}