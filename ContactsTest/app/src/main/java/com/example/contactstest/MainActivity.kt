package com.example.contactstest

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity()
{
    private val contactList= ArrayList<String>()
    private lateinit var adapt: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapt= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactList)
        val contactsView=findViewById<ListView>(R.id.contactsView)
        contactsView.adapter=adapt
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_CONTACTS),1)
        }
        else
        {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    )
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            1->
            {
                if(grantResults.isNotEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    readContacts()
                }
                else
                {
                    Toast.makeText(this, "You denied the permission",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun readContacts()
    {
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while(moveToNext())
            {
                val displayName=getString(getColumnIndexOrThrow((ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)))
                val number=getString(getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactList.add("$displayName\n$number")
            }
            adapt.notifyDataSetChanged()
            close()
        }
    }
}