package com.example.broadcastbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.login)
        val accountEdit = findViewById<EditText>(R.id.accountEdit)
        val passwordEdit = findViewById<EditText>(R.id.passwordEdit)
        val pref = getPreferences(Context.MODE_PRIVATE)
        val isRemember = pref.getBoolean("isremember", false)
        if (isRemember)
        {
            accountEdit.setText(pref.getString("Account", ""))
            passwordEdit.setText(pref.getString("Password", ""))
        }
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            val isremember = findViewById<CheckBox>(R.id.rememberpass)
            if (account == "admin" && password == "123456")
            {
                val editor = pref.edit()
                if (isremember.isChecked)
                {
                    editor.putString("Account", account)
                    editor.putString("Password", password)
                    editor.putBoolean("isremember", true)
                } else editor.clear()
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else
            {
                Toast.makeText(this, "account or password is invaild", Toast.LENGTH_SHORT).show()
            }
        }
    }
}