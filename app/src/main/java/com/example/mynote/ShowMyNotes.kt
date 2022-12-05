package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_my_nodes.*
import java.util.*

class ShowMyNotes : AppCompatActivity() {
    lateinit var notesDBHelper:NotesDBHelper
    lateinit var nodeAdapter:NoteAdapter
    var array:ArrayList<DataBase> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_nodes)

        notesDBHelper=NotesDBHelper(applicationContext)
        readData()

        fab.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun readData(){
        val cursor=notesDBHelper.readableDatabase.rawQuery(
            "SELECT * FROM ${DB.TABLE_TITLE}",
            arrayOf<String>()
        )

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val body = cursor.getString(2)
            val date = cursor.getString(3)

            val dataBase=DataBase(id,title,body,date)
            array.add(dataBase)
        }
        nodeAdapter= NoteAdapter(array)
        myRecycler.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        myRecycler.adapter=nodeAdapter
    }
}