package com.example.quizmaster.repository

import android.content.Context
import com.example.quizmaster.R
import com.example.quizmaster.model.Question
import com.example.quizmaster.model.QuizResult
import com.example.quizmaster.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * QuizRepository poskytuje metody pro práci s otázkami a historií kvízů
 *
 * @param context Kontext aplikace
 */
class QuizRepository(private val context: Context)
{
    /**
     * Metoda slouží k načtení seznamu otázek z JSON souboru umístěného v raw resources
     *
     * @return Vrací seznam datového typu Question
     */
    fun loadQuestions(): List<Question>
    {
        val json = JsonUtil.loadJSON(context, R.raw.questions)
        val type = object : TypeToken<List<Question>>() {}.type
        return Gson().fromJson(json, type)
    }

    /**
     * Metoda slouží k načtení historie výsledků kvízů ze souboru "history.json"
     *
     * @return Vrací mutabilní seznam datového typu QuizResult. Pokud soubor neexistuje,
     * vrátí prázdný seznam
     */
    fun loadHistory(): MutableList<QuizResult>
    {
        val json = JsonUtil.readSavedJSON(context, "history.json") ?: return mutableListOf()
        val type = object : TypeToken<MutableList<QuizResult>>() {}.type
        return Gson().fromJson(json, type)
    }

    /**
     * Metoda slouží k uložení historie výsledků kvízů do interního souboru "history.json"
     *
     * @param history Mutabilní seznam datového typu QuizResult, který se má uložit
     */
    fun saveHistory(history: MutableList<QuizResult>)
    {
        val json = Gson().toJson(history)
        JsonUtil.saveJSON(context, "history.json", json)
    }
}