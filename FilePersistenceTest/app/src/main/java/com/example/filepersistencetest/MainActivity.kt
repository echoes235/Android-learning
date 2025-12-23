package com.example.filepersistencetest

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val inputText = load()
        if (inputText.isNotEmpty())
        {
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun load(): String
    {
        val content = StringBuilder()
        try
        {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine { content.append(it) }
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
        return content.toString()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        val editText = findViewById<EditText>(R.id.editText)
        val inputText = editText.text.toString()
        save(inputText)
    }

    private fun save(inputText: String)
    {
        try
        {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        }
        catch (e: IOException)
        {
            return e.printStackTrace()
        }
    }
}