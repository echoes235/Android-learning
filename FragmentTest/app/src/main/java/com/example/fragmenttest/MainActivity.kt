package com.example.fragmenttest

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frag=supportFragmentManager.findFragmentById(R.id.leftFrag) as LeftFragment
        val button1=frag.view?.findViewById<Button>(R.id.button)//可以这么写,但是不安全,这个时候button可能为空(因为frag还没inflate出来)
        supportFragmentManager.setFragmentResultListener(LeftFragment.REQ_RIGHT,this){
            _,bundle->
            when(bundle.getString(LeftFragment.KEY_ACTION))
            {
                LeftFragment.ACTION_SHOW_ANOTHER->replaceFragment(AnotherRightFragment())
            }
        }
        if(savedInstanceState==null)
        {
           replaceFragment(RightFragment())
        }
    }
    private  fun replaceFragment(fragment:Fragment)
    {
//        val fragmentManager=supportFragmentManager
//        val transaction=fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
    }
}