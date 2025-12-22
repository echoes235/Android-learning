package com.example.activitylearn

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThridActivity :BaseActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d("ThridActivity","task is $taskId")
        setContentView(R.layout.activity_thrid)
        val button3 :Button=findViewById(R.id.button3)
        button3.setOnClickListener{
            ActivityCollector.finishiall()
        }
    }
}