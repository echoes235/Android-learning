package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogoActivity : AppCompatActivity()
{
    private lateinit var countdown:TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        countdown=findViewById(R.id.tvcountdown)
        val timer=object:CountDownTimer(5000,1000){
            override fun onTick(millisUntilFinished: Long)
            {
                val secondleft=(millisUntilFinished/1000).toInt()
                countdown.text="${secondleft}秒后进入点菜页面"
            }

            override fun onFinish()
            {
                startActivity(Intent(this@LogoActivity,MainActivity::class.java))
                finish()
            }
        }.start()
    }

}