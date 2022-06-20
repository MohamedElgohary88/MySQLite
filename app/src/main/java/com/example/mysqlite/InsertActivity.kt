package com.example.mysqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_main.insert

class InsertActivity : AppCompatActivity() {

    internal var helper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        insert.setOnClickListener {
            if (first_name_update?.text!!.isEmpty() || last_name_update?.text!!.isEmpty()){
                first_name_update.error = "filed required"
                last_name_update.error = "filed required"
                Toast.makeText(this," Failed Inserted " , Toast.LENGTH_SHORT).show()
            }else{
                helper.insertData(
                    first_name_update.text.toString().trim(),
                    last_name_update.text.toString().trim(),
                    age_update.text.toString().trim(),
                    address_update.text.toString().trim(),
                    department_update.text.toString().trim())
                Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
            }

        }
    }
}