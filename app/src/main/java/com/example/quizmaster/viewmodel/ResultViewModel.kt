package com.example.quizmaster.viewmodel

import androidx.lifecycle.ViewModel

/**
 * ViewModel pro zpracování a výpočet výsledků kvízu
 */
class ResultViewModel : ViewModel()
{
    /**
     * Metoda slouží k výpočtu procentuální úspěšnosti kvízu
     *
     * @param score Počet správně zodpovězených otázek
     * @param total Celkový počet otázek v kvízu
     * @return Procentuální úspěšnost jako celé číslo (0-100)
     */
    fun calculatePercentage(score: Int, total: Int): Int
    {
        return if (total > 0) (score.toDouble() / total * 100).toInt() else 0
    }

    /**
     * Metoda slouží k rozhodnutí rozhoduje, zda je výsledek považován za úspěšný (hranice 50 %)
     * následně určí barvu textu v UI
     *
     * @param percentage Vypočtená úspěšnost v procentech
     * @return Vrací true, pokud je úspěšnost 50 % a více, jinak false
     */
    fun isSuccess(percentage: Int): Boolean
    {
        return percentage >= 50;
    }
}