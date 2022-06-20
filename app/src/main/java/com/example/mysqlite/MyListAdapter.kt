package com.example.mysqlite

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyListAdapter(var mCtx:Context , var resources:Int , var items : List<Employee>)
    : ArrayAdapter<Employee>(mCtx , resources , items){

    private var helper = DatabaseHelper(mCtx)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mCtx)
        val view:View =  layoutInflater.inflate(resources,null)

        val department :TextView = view.findViewById(R.id.department2)
        val firstName : TextView = view.findViewById(R.id.first_name2)
        val lastName : TextView = view.findViewById(R.id.last_name2)

        val update :Button = view.findViewById(R.id.update)
        val delete : Button = view.findViewById(R.id.delete)

        val employee = items[position]
        firstName.text = employee.firstname
        lastName.text = employee.lastname
        department.text = employee.department

        update.setOnClickListener {
            updateInfo(employee)
        }
        delete.setOnClickListener {
            delete(employee)
        }

        return view

    }
    private fun delete(employee: Employee){
        helper.deleteData(employee.id)
    }

    private fun updateInfo(employee: Employee) {
        val builder = AlertDialog.Builder(mCtx)
        val inflater = LayoutInflater.from(mCtx)
        val view =  inflater.inflate(R.layout.employee_update,null)

        val firstName : EditText = view.findViewById(R.id.first_name_update)
        val lastName : EditText = view.findViewById(R.id.last_name_update)
        val age :EditText = view.findViewById(R.id.age_update)
        val address :EditText = view.findViewById(R.id.address_update)
        val department :EditText = view.findViewById(R.id.department_update)

        firstName.setText(employee.firstname)
        lastName.setText(employee.lastname)
        age.setText(employee.age)
        address.setText(employee.address)
        department.setText(employee.department)

        builder.setView(view)

        builder.setPositiveButton("Update",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val isUpdate = helper.updateData(employee.id,
                    firstName.text.toString().trim(),
                    lastName.text.toString().trim(),
                    age.text.toString().trim(),
                    address.text.toString().trim(),
                    department.text.toString().trim())

                if (isUpdate == true)
                    Toast.makeText(mCtx,"Updated :)" , Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(mCtx , "Failed Update :(" ,Toast.LENGTH_SHORT).show()

            }
        })
        builder.setNegativeButton("Cancel",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }
        })

        val alert = builder.create()
        alert.show()
    }

}