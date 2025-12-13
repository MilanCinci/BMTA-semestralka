package com.example.quizmaster.util

import android.content.Context

/**
 * JsonUtil poskytuje jednoduché metody pro práci s JSON soubory
 */
object JsonUtil
{

    /**
     * Metoda slouží k načtení JSON souboru z raw resource složky a vrátí jeho obsah jako String
     *
     * @param context Kontext aplikace
     * @param resId ID resource souboru (např. R.raw.filename)
     * @return Vrací obsah JSON souboru jako String
     */
    fun loadJSON(context: Context, resId: Int): String
    {
        return context.resources.openRawResource(resId).bufferedReader().use {
            it.readText()
        }
    }

    /**
     * Metoda slouží k uložení JSON Stringu do úložiště aplikace pod vybraným názvem souboru
     *
     * @param context Kontext aplikace
     * @param filename Název souboru
     * @param json Obsah JSON, který se má uložit
     */
    fun saveJSON(context: Context, filename: String, json: String)
    {
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    /**
     * Metoda slouží k načtení JSON soubor z úložiště aplikace
     *
     * @param context Kontext aplikace
     * @param filename Název souboru
     * @return Vrací celý obsah JSON souboru jako String, pokud nastane výjimka vrátí NULL
     */
    fun readSavedJSON(context: Context, filename: String): String?
    {
        return try
        {
            context.openFileInput(filename).bufferedReader().use {
                it.readText()
            }
        }

        catch (ex: Exception)
        {
            null
        }
    }
}
