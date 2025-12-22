package com.example.orderapp

import android.os.Bundle
import android.text.InputFilter
import android.text.method.DigitsKeyListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    private lateinit var ivFood:ImageView
    private lateinit var tvFoodName:TextView
    private lateinit var btprev: Button
    private lateinit var btnext: Button
    private lateinit var edperson: EditText
    private lateinit var seekscore: SeekBar
    private lateinit var tvscore: TextView
    private lateinit var rgUtensils: RadioGroup
    private lateinit var rbNeed: RadioButton
    private lateinit var rbNoNeed: RadioButton

    private lateinit var cbTea: CheckBox
    private lateinit var cbTissue: CheckBox
    private lateinit var cbStraw: CheckBox

    private lateinit var btnSubmit: Button

    private data class Food(val name:String,val imageRes:Int)
    private val foods= listOf(Food("小吃套餐",R.drawable.food_1),Food("炸鸡套餐",R.drawable.food_2),Food("汉堡套餐",R.drawable.food_3))
    private var index=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivFood = findViewById(R.id.tvFoodimage)
        tvFoodName = findViewById(R.id.tvFoodname)
        btprev = findViewById(R.id.btPrev)
        btnext = findViewById(R.id.btNext)

        edperson = findViewById(R.id.etPerson)
        seekscore = findViewById(R.id.seekScore)
        tvscore = findViewById(R.id.tvscore)

        rgUtensils = findViewById(R.id.rgroup)
        rbNeed = findViewById(R.id.cneed)
        rbNoNeed = findViewById(R.id.noneed)

        cbTea = findViewById(R.id.cbtea)
        cbTissue = findViewById(R.id.cbTissue)
        cbStraw = findViewById(R.id.cbstraw)

        btnSubmit = findViewById(R.id.btsubmit)

        edperson.keyListener=DigitsKeyListener.getInstance("0123456789")//限制只能输入这些字符,防止用户复制粘贴
        edperson.filters= arrayOf(InputFilter.LengthFilter(2))//设置最多两个字符
        showFood()
        btprev.setOnClickListener{
            index=(index-1+foods.size)%foods.size
            showFood()
        }
        btnext.setOnClickListener {
            index=(index+1)%foods.size
            showFood()
        }
        seekscore.max=100
        seekscore.progress=0
        tvscore.text="0"
        seekscore.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
            {
                tvscore.text=progress.toString()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
        })
        btnSubmit.setOnClickListener {
            submit()
        }
    }
    private fun showFood()
    {
        var f=foods[index]
        ivFood.setImageResource(f.imageRes)
        tvFoodName.text=f.name
    }
    private fun submit()
    {
        val person=edperson.text.toString().trim()//trim是去掉首尾空格的,edperson的text是Editable类型,要转成String
        if(person.isEmpty())
        {
            Toast.makeText(this,"请输入用餐人数",Toast.LENGTH_SHORT).show()
            return
        }
        val num=person.toIntOrNull()
        if(num==null)
        {
            Toast.makeText(this,"人数格式不正确",Toast.LENGTH_SHORT).show()
            return
        }
        val checkId=rgUtensils.checkedRadioButtonId//返回当前被选中的id,如果没有就返回-1
        if(checkId==-1)
        {
            Toast.makeText(this,"请选择是否需要餐具",Toast.LENGTH_SHORT).show()
            return
        }
        val is_need=if(checkId==rbNeed.id)"需要" else "不需要"
        val score=seekscore.progress
        val extras= mutableListOf<String>()
        if(cbTea.isChecked)extras.add("茶水")
        if(cbStraw.isChecked)extras.add("吸管")
        if(cbTissue.isChecked)extras.add("纸巾")
        Toast.makeText(this,"您已经提交成功",Toast.LENGTH_SHORT).show()
    }
}