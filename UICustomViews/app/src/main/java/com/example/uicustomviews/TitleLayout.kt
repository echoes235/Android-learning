package com.example.uicustomviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class TitleLayout(context: Context, attrs:AttributeSet):LinearLayout(context,attrs)
{
    init
    {
        LayoutInflater.from(context).inflate(R.layout.title,this)
        val titleback: Button =findViewById(R.id.tietleBack)
        titleback.setOnClickListener {
            val activity=context as Activity
            activity.finish()
        }
        val titleedit:Button=findViewById(R.id.tietleEdit)
        titleedit.setOnClickListener {
            Toast.makeText(context,"你点了编辑按钮",Toast.LENGTH_SHORT).show()
        }
    }

}