package com.example.activitylearn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : BaseActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity","task is $taskId")
        setContentView(R.layout.firstlayout)
//        val button1: Button =findViewById(R.id.button1)
//        button1.setOnClickListener{
//            Toast.makeText(this,"You clicked Button 1",Toast.LENGTH_SHORT).show()
//        }
        //简洁写法
        val button1:Button=findViewById(R.id.button1)
        button1.setOnClickListener{
//            Toast.makeText(this,"You clikced Button 1",Toast.LENGTH_SHORT).show()
//            finish()//添加finish后代码运行完就会销毁当前Activity
//            val intent= Intent(this,SecActivity::class.java)//显式使用intent
            //隐式使用intent
//            val intent=Intent("com.example.activitylearn.ACTION_START")
//            intent.addCategory("com.example.activitylearn.MY_CATEGORY")
//            startActivity(intent)

            //还可以调用外部网站
//            val intent =Intent(Intent.ACTION_VIEW)
//            intent.data= Uri.parse("https://www.baidu.com")
//            startActivity(intent)

            //传递数据
//            val data="Hello secondacti"
//            val intent=Intent(this,SecActivity::class.java)
//            intent.putExtra("extradata",data)
//            startActivity(intent)

            //使用companion pbject后
            SecActivity.actionStart(this,"data1","data2")
        }
    }
//创建菜单,但是你用代码写好的xml文件,要用menuInflater才能让他真正显示出来
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
//对于客户点哪个会出现什么还需要重写
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.add_item->Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show()
            R.id.remove_item->Toast.makeText(this,"You Clicked Remove",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}