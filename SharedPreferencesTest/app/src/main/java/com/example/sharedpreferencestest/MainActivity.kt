package com.example.sharedpreferencestest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savebutton=findViewById<Button>(R.id.saveButton)
        savebutton.setOnClickListener {
            val editor=getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putInt("age",28)
            editor.putString("name","Tom")
            editor.putBoolean("married",false)
            editor.apply()
            //使用高阶函数优化后
            getSharedPreferences("data", Context.MODE_PRIVATE).edit{
                putInt("age",28)
                putString("name","Tom")
                putBoolean("married",false)
            }
            //KTX扩展库已经加入了这个功能,直接(open换成edit)调用edit就行
        }
        val restoreButton=findViewById<Button>(R.id.restoreButton)
        restoreButton.setOnClickListener {
            val prefs=getSharedPreferences("data", Context.MODE_PRIVATE)
            val name=prefs.getString("name","")
            val age=prefs.getInt("age",0)
            val married=prefs.getBoolean("married",false)
            Log.d("MainActivity", "name is $name")
            Log.d("MainActivity", "age is $age")
            Log.d("MainActivity", "married is $married")
        }
    }
}