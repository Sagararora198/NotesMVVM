package com.example.notesmvvm.model

import android.util.Log

class NoteRepository {

    private val notes = mutableListOf<Note>()

    fun getNotes():List<Note>{
        return notes
    }
    fun addNote(note:Note){

        notes.add(note)
    }
    fun getNoteById(id:Int):Note?{
        Log.d("repoId", "getNoteById:$id ")
        val getNote = notes.find { it.id == id }
        Log.d("repoNote", "getNoteById:${getNote?.title} ${getNote?.body}")
        return notes.find { it.id == id }

    }
    fun updateNote(id:Int,title:String,body:String){
        val note  = notes.find { it.id==id }
        note?.let {
            it.title = title
            it.body = body
        }
    }
}