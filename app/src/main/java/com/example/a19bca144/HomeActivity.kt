package com.example.a19bca144

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var prefer=getSharedPreferences("MyPref", MODE_PRIVATE)

        btnInsert.setOnClickListener{
            var fname = edtPrFname.text.toString()
            var lname = edtPrLname.text.toString()
            var age = edtPrAge.text.toString().toInt()
            var person = Person(fname,lname,age)
            var db = DBHelper(this)
            var flag = db.insert(person)
            if(flag)
            {
                Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show()
                edtPrFname.setText("")
                edtPrLname.setText("")
                edtPrAge.setText("")
            }
        }

        btnViewAll.setOnClickListener{
            var intent = Intent(this,ViewAllActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id=item.itemId
        if(id==R.id.item1)
        {
            var prefer=getSharedPreferences("MyPref", MODE_PRIVATE)
            var editor=prefer.edit()
            editor.clear()
            editor.commit()
            var intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(applicationContext,"Logout successfully",Toast.LENGTH_LONG).
            show()
        }
        else if(id==R.id.item2)
        {
            Toast.makeText(applicationContext,"Item2",Toast.LENGTH_LONG).
            show()
        }
        return super.onOptionsItemSelected(item)
    }
}