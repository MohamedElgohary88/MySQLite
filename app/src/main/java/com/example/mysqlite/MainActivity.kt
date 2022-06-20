package com.example.mysqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insert.setOnClickListener {
            val intent = Intent(this,InsertActivity::class.java)
            startActivity(intent)
        }
        database.setOnClickListener {
            val intent = Intent(this,DataBaseActivity::class.java)
            startActivity(intent)
        }
    }
}