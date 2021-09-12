package com.nbaengine.todo_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbaengine.todo_app.R
import com.nbaengine.todo_app.adapters.Notes_Adapter
import com.nbaengine.todo_app.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notesAdapter: Notes_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesAdapter = Notes_Adapter(mutableListOf())


        notes_recycler_views.adapter = notesAdapter
        notes_recycler_views.layoutManager =  LinearLayoutManager(this)

        add_note.setOnClickListener {
            val title = note_value.text.toString()
            if (title.isNotEmpty()){
                val note = Todo(title)
                notesAdapter.addNoes(note)
                note_value.text.clear()
            }
        }


        delete_note.setOnClickListener {
            notesAdapter.deleteNotes()
        }


    }
}