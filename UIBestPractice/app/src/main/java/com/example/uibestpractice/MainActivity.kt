package com.example.uibestpractice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{
    private val msgList=ArrayList<Msg>()
    private  lateinit var adapter:MsgAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        val inputText:EditText=findViewById(R.id.inputText)
        val send: Button =findViewById(R.id.send)
        initmsg()
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter=MsgAdapter(msgList)
        recyclerView.adapter=adapter
        send.setOnClickListener {
            val content=inputText.text.toString()
            if(content.isNotEmpty())
            {
                val msg=Msg(content,Msg.TYPE_SENT)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size-1)
                recyclerView.scrollToPosition(msgList.size-1)
                inputText.setText("")
            }
        }
    }
    private fun initmsg()
    {
        msgList.add(Msg("Hello guy.", Msg.TYPE_RECEIVED))
        msgList.add(Msg("Hello. Who is that?", Msg.TYPE_SENT))
        msgList.add(Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED))
    }
}