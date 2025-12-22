package com.example.ordermyself

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ordermyself.R

class LogoActivity : AppCompatActivity()
{
    private lateinit var countdown:TextView
    companion object{
        private  var alreadydown=false
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        if(alreadydown)
        {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        alreadydown=true
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
                startActivity(Intent(this@LogoActivity, MainActivity::class.java))
                finish()
            }
        }.start()
    }

}