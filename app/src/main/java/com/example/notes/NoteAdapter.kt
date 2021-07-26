package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val listner:onCustomClickListner): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()

    inner class NoteViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val textView: TextView =itemview.findViewById<TextView>(R.id.note)
        val delete: ImageView =itemview.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
      viewHolder.delete.setOnClickListener {
          listner.onclick(allNotes[viewHolder.adapterPosition])

      }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote=allNotes[position]
//        holder.textView.text=currentNote.text
        holder.textView.text=currentNote.text

    }
    fun updateList(newList:List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    interface onCustomClickListner {
        fun onclick(note:Note)
    }
}