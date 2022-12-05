package com.example.mynote

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_note_details.*
import kotlinx.android.synthetic.main.note_item.*
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*


class ShowNoteDetails : AppCompatActivity() {

    lateinit var getTitle:String
    lateinit var getBody:String
    lateinit var getId:String
    lateinit var notesDBHelper:NotesDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_note_details)

        // to get data from intent
        getTitle=intent.extras?.getString("title").toString()
        edt_titleDetails.setText(getTitle)
        getBody=intent.extras?.getString("body").toString()
        edt_bodyDetails.setText(getBody)
        getId=intent.extras?.getString("id").toString()
        notesDBHelper=NotesDBHelper(applicationContext)

        currentDate()
        // to delete row
        delete_image.setOnClickListener {
            dialogToDelete()
        }
        //to save change
        txt_saveChange.setOnClickListener {
            dialogToSaveChange()
        }
    }


    fun getItemFromIntent(){

            //to delete a row
            notesDBHelper.deleteRow(getId)

            // to save the change in the row
            val title = edt_titleDetails.text.toString()
            val body = edt_bodyDetails.text.toString()
            val data = txt_dateDetails.text.toString()

            val newEntry = ContentValues()
            newEntry.put(DB.ID,getId)
            newEntry.put(DB.TITLE,title)
            newEntry.put(DB.BODY,body)
            newEntry.put(DB.DATE,data)
            notesDBHelper.writableDatabase.insert(DB.TABLE_TITLE,null,newEntry)
            Toast.makeText(this,"The Node Saved", Toast.LENGTH_SHORT).show()
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
        txt_dateDetails.text = currentDateAndTime
    }

    fun dialogToDelete(){
        val getId:String=intent.extras?.getString("id").toString()
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogDeleteTitle)
        //set message for alert dialog
       // builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            notesDBHelper.deleteRow(getId)
            Toast.makeText(applicationContext,"The node deleted ",Toast.LENGTH_LONG).show()
            val intent= Intent(this,ShowMyNotes::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
            startActivity(intent)
            finish()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(applicationContext,"operation cancel",Toast.LENGTH_LONG).show()
        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"The node is not delete",Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun dialogToSaveChange(){
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogSaveChangeTitle)
        //set message for alert dialog
        // builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            getItemFromIntent()
            Toast.makeText(applicationContext,"The change done",Toast.LENGTH_LONG).show()
            val intent= Intent(this,ShowMyNotes::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
            startActivity(intent)
            finish()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(applicationContext,"operation cancel",Toast.LENGTH_LONG).show()
        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"The change is not save",Toast.LENGTH_LONG).show()
//            val intent= Intent(this,ShowMyNotes::class.java)
//            startActivity(intent)
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}