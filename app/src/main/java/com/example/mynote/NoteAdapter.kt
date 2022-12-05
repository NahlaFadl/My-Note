package com.example.mynote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.*

class NoteAdapter(val nodesList: ArrayList<DataBase>)
    : RecyclerView.Adapter<NoteAdapter.NodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NodeViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NodeViewHolder, position: Int) {

        holder.txtTitle.text=nodesList[position].TITLE.toString()
        val context:Context=holder.txtTitle.context
        holder.itemView.setOnClickListener {
            val intent= Intent(context,ShowNoteDetails::class.java)
            intent.putExtra("title",nodesList[position].TITLE.toString())
            intent.putExtra("body",nodesList[position].BODY.toString())
            intent.putExtra("id",nodesList[position].ID.toString())
            context.startActivity(intent)
        }

        holder.txtDateDetails.text=nodesList[position].DATE
    }

    override fun getItemCount(): Int {
        return nodesList.size
    }

    class NodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtTitle =itemView.txt_title as TextView
        val txtDateDetails=itemView.txt_dateShowDetails as TextView
    }
}