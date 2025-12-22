package com.example.activitylearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : BaseActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d("SecActivity","task is $taskId")
        setContentView(R.layout.activity_sec)
        val extra=intent.getStringExtra("param1")//get后面写什么依赖于传递数据是什么
        Log.d("SecActivity","extra messege is $extra")

        //体验singleinstance启动方式
        val button2: Button =findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent= Intent(this,ThridActivity::class.java)
            startActivity(intent)
        }
    }
    //这么写可以明确到底要传什么数据进去
    companion object {
        fun actionStart(context: Context,data1:String,data2:String)
        {
            val intent =Intent(context,SecActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data2)
            context.startActivity(intent)
        }
    }
}