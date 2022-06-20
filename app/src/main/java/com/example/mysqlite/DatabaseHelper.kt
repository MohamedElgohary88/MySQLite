package com.example.mysqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    :SQLiteOpenHelper(context,DATABASE_NAME,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table  $TABLE_NAME ( ID INTEGER PRIMARY  KEY AUTOINCREMENT,"
        + " firstname TEXT ,  lastname TEXT , age INTEGER , address TEXT , department TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    }

fun insertData(firstname :String, lastname :String , age :String , address :String, department :String){
    val db = this.writableDatabase
    val contentValues = ContentValues()
    contentValues.put(col_2 , firstname)
    contentValues.put(col_3 , lastname)
    contentValues.put(col_4 , age)
    contentValues.put(col_5 , address)
    contentValues.put(col_6 , department)
    db.insert(TABLE_NAME,null,contentValues)
    }

val allDAta : Cursor
get() {
    val db = this.writableDatabase
    val res = db.rawQuery("select * from " + TABLE_NAME , null)
    return res
}

fun updateData(id :String , firstname :String, lastname :String
               , age :String , address :String, department :String): Boolean {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(col_1 , id)
        contentValues.put(col_2 , firstname)
        contentValues.put(col_3 , lastname)
        contentValues.put(col_4 , age)
        contentValues.put(col_5 , address)
        contentValues.put(col_6 , department)
        db.update(TABLE_NAME,contentValues,"ID = ? ", arrayOf(id))
    return true
    }

    fun deleteData(id: String) : Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME , "ID = ? " , arrayOf(id))
    }

        companion object{
            val DATABASE_NAME = "Employee.db"
            val TABLE_NAME = "employee_table"
            val col_1 = "ID"
            val col_2 = "firstname"
            val col_3 = "lastname"
            val col_4 = "age"
            val col_5 = "address"
            val col_6 = "department"
        }
}