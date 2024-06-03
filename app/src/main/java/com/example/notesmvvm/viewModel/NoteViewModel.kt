package com.example.notesmvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.model.NoteRepository

class NoteViewModel:ViewModel(){
    private val repository = NoteRepository()
    private val _notes = MutableLiveData<List<Note>>()
    val notes:LiveData<List<Note>> = _notes

    init {
        _notes.value = repository.getNotes()
    }
    fun addNote(note:Note){
        repository.addNote(note)
        _notes.value = repository.getNotes()

    }
    fun getNoteById(id:Int):Note?{
        return repository.getNoteById(id)

    }
    fun updateNote(id:Int,title:String,body:String){
        repository.updateNote(id,title,body)
        _notes.value = repository.getNotes()
    }
}