package com.example.notesmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.databinding.NoteBinding
import com.example.notesmvvm.model.Note

class NotesAdapter(private var notes:List<Note>,private val onNoteClicked:(Note)->Unit)
    :RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
        inner class MyViewHolder(private val binding:NoteBinding):RecyclerView.ViewHolder(binding.root){

            fun bind(note:Note){
                binding.note = note
                binding.executePendingBindings()
                binding.root.setOnClickListener{
                    onNoteClicked(note)

                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(notes[position])
    }
    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

}