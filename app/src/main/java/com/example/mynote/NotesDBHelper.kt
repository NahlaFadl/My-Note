package com.example.mynote

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID

class NotesDBHelper(context: Context) :
    SQLiteOpenHelper(context,DBNAME,null,DBVERSION) {

    lateinit var db:DB
        companion object{
            private const val DBNAME = "NotesDataBase"
            private const val DBVERSION =1
        }

    override fun onCreate(db: SQLiteDatabase?) {

        val noteSql = "CREATE TABLE ${DB.TABLE_TITLE}("+
                "${DB.ID} INTEGER PRIMARY KEY,"+
                "${DB.TITLE} TEXT,"+
                "${DB.BODY} TEXT, "+
                "${DB.DATE} TEXT "+
                ")"
        db?.execSQL(noteSql)
    }
    fun deleteRow(value:String) {
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM " + DB.TABLE_TITLE + " WHERE " + DB.ID + "='" + value + "'");
        db.close();
    }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}