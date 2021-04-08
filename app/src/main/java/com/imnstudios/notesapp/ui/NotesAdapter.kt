package com.imnstudios.notesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imnstudios.notesapp.R
import com.imnstudios.notesapp.data.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter(private val notesList: List<Note>, val noteClick: NoteClick) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun View.drawUI(note: Note) {
            setOnClickListener { noteClick.onNoteClicked(adapterPosition) }

            note.title.apply {
                if (this.isEmpty())
                    title.visibility = View.GONE
                else
                    title.text = this
            }

            note.description.apply {
                if (this.isEmpty())
                    description.visibility = View.GONE
                else
                    description.text= this
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        holder.apply {
            itemView.drawUI(note)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    interface NoteClick {
        fun onNoteClicked(position: Int)
    }

    fun getItemAt(position: Int): Note {
        return notesList[position]
    }
}