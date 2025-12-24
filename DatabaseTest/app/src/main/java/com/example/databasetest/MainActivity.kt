package com.example.databasetest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val creatDatabase = findViewById<Button>(R.id.createDatabase)
        creatDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        val addData = findViewById<Button>(R.id.addData)
        //增加数据
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
            //可以通过ktx里面优化的方式来输入数据,用类似mapOf的方法定义
            val values = contentValuesOf("name" to "Game of Thrones", "author" to "George Martin",
                "pages" to 720, "price" to 20.85)

        }
        //更新数据
        val updateData=findViewById<Button>(R.id.updateData)
        updateData.setOnClickListener {
            val db=dbHelper.writableDatabase
            val values= ContentValues()
            values.put("price","10.99")
            db.update("Book",values,"name=?",arrayOf("The Da Vinci Code"))
        }
        //删除数据
        val deleteDate=findViewById<Button>(R.id.deleteData)
        deleteDate.setOnClickListener {
            val db=dbHelper.writableDatabase
            db.delete("Book","pages>?",arrayOf("500"))
        }
        //查找数据
        val queryData=findViewById<Button>(R.id.queryData)
        queryData.setOnClickListener {
            val db=dbHelper.writableDatabase
            val cursor=db.query("Book",null,null,null,null,null,null)
            if(cursor.moveToFirst())
            {
                do
                {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val author = cursor.getString(cursor.getColumnIndexOrThrow("author"))
                    val pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                }while (cursor.moveToNext())
            }
        }
        //事务的运用
        val replaceData=findViewById<Button>(R.id.replaceData)
        replaceData.setOnClickListener {
            val db=dbHelper.writableDatabase
            db.beginTransaction()
            try
            {
                db.delete("Book",null,null)
                if(true)
                {
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            }catch (e: IOException)
            {
                e.printStackTrace()
            }
            finally
            {
                db.endTransaction()
            }
        }
    }
}