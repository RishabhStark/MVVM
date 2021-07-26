package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),NoteAdapter.onCustomClickListner {

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv= findViewById<RecyclerView>(R.id.recyclerview)
        rv.layoutManager=LinearLayoutManager(this)
        val adapter=NoteAdapter(this,this)
        rv.adapter=adapter

        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
         viewModel.allNotes.observe(this, Observer {
             list->list?.let {
             adapter.updateList(it)
         }

         })


    }

    override fun onclick(note: Note) {
  viewModel.deleteNote(note)
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText=findViewById<EditText>(R.id.edit).text.toString()
        if(!noteText.equals("")) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Inserted",Toast.LENGTH_SHORT).show()

        }
    }
}