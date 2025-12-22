package com.example.ordermyself

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
import com.example.ordermyself.R
class MainActivity : AppCompatActivity()
{
    private lateinit var ivFood: ImageView
    private lateinit var tvFoodName: TextView
    private lateinit var btprev: Button
    private lateinit var btnext: Button
    private lateinit var seekscore: SeekBar
    private lateinit var tvscore: TextView
    private lateinit var rgUtensils: RadioGroup
    private lateinit var rbNeed: RadioButton
    private lateinit var rbNoNeed: RadioButton

    private lateinit var btnSubmit: Button

    private data class Food(val name:String,val imageRes:Int)
    private val foods= listOf(Food("原神启动!", R.drawable.tmp2),Food("《原神的天赋》",
        R.drawable.tmp4
    ),Food("原洲双修", R.drawable.tmp5))
    private var index=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivFood = findViewById(R.id.tvFoodimage)
        tvFoodName = findViewById(R.id.tvFoodname)
        btprev = findViewById(R.id.btPrev)
        btnext = findViewById(R.id.btNext)

        seekscore = findViewById(R.id.seekScore)
        tvscore = findViewById(R.id.tvscore)

        rgUtensils = findViewById(R.id.rgroup)
        rbNeed = findViewById(R.id.cneed)
        rbNoNeed = findViewById(R.id.noneed)

        btnSubmit = findViewById(R.id.btsubmit)

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
        seekscore.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {
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
        val checkId=rgUtensils.checkedRadioButtonId//返回当前被选中的id,如果没有就返回-1
        if(checkId==-1)
        {
            Toast.makeText(this, "请选择是否需要餐具", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "您已经提交成功", Toast.LENGTH_SHORT).show()
    }
}