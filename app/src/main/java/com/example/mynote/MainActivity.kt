package com.example.mynote

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var  notesDBHelper:NotesDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesDBHelper=NotesDBHelper(applicationContext)

        addItem()
        currentDate()

    }

    fun addItem(){
        txt_save.setOnClickListener {
            val title = edt_title.text.toString()
            val body = edt_body.text.toString()
            val data = txt_date.text.toString()

            val newEntry = ContentValues()
            newEntry.put(DB.TITLE,title)
            newEntry.put(DB.BODY,body)
            newEntry.put(DB.DATE,data)
            notesDBHelper.writableDatabase.insert(DB.TABLE_TITLE,null,newEntry)
            Toast.makeText(this,"The Node Saved",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,ShowMyNotes::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
            startActivity(intent)
            finish()
        }
    }


    fun currentDate(){

        // on below line we are creating and initializing
        // variable for simple date format.
       // val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z")
        val sdf = SimpleDateFormat("dd-MM-yyyy  HH:mm")
        // on below line we are creating a variable for
        // current date and time and calling a simple
        // date format in it.
        val currentDateAndTime = sdf.format(Date())

        // on below line we are setting current
        // date and time to our text view.
        txt_date.text = currentDateAndTime
    }
}