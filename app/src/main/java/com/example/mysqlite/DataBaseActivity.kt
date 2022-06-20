package com.example.mysqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data_base.*

class DataBaseActivity : AppCompatActivity() {

    private var helper = DatabaseHelper(this)
    private var list = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        viewAll()

        val adapter = MyListAdapter(this,R.layout.employee,list)
        list_view.adapter = adapter

        refresh.setOnClickListener {
            viewAll()
            adapter.notifyDataSetChanged()
        }
    }

    private fun viewAll() {
        list.clear()
        val res = helper.allDAta
        if (res.count == 0){
            Toast.makeText(this,"No Records :( " , Toast.LENGTH_LONG).show()
        }
        while (res.moveToNext()){
            list.add(Employee(res.getString(0),
                              res.getString(1),
                              res.getString(2),
                              res.getString(3),
                              res.getString(4),
                              res.getString(5))
            )
        }
    }
}