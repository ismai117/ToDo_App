package com.nbaengine.todo_app.adapters

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nbaengine.todo_app.R
import com.nbaengine.todo_app.model.Todo
import kotlinx.android.synthetic.main.item_layout.view.*

class Notes_Adapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<Notes_Adapter.NoteViewHolder>() {

    class NoteViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_layout,
                    parent,
                    false
                )
        )
    }

    fun addNoes(todo: Todo){
        todos.add(todo)
        notifyItemChanged(todos.size - 1)
    }

    fun deleteNotes(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun LineThrough(notesValue: TextView, checkbox: Boolean){
        if (checkbox){
            notesValue.paintFlags = notesValue.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            notesValue.paintFlags = notesValue.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNotes = todos[position]
        holder.itemView.apply {
            notes_value.text = currentNotes.title
            checkbox_value.isChecked = currentNotes.isChecked
            LineThrough(notes_value, checkbox_value.isChecked)

            checkbox_value.setOnCheckedChangeListener { _, isChecked ->
                LineThrough(notes_value, checkbox_value.isChecked)
                currentNotes.isChecked = !currentNotes.isChecked
            }
        }


    }

    override fun getItemCount(): Int {
        return todos.size
    }

}