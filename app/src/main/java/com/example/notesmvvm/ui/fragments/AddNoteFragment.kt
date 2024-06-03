package com.example.notesmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesmvvm.databinding.FragmentAddNoteBinding
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.ui.MainActivity
import com.example.notesmvvm.viewModel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNoteFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentAddNoteBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()


    }

    private fun initialize() {
        // initialize view model
        viewModel = (activity as MainActivity).viewModel

        // setup save button event
        binding.saveNoteButton.setOnClickListener {
            // convert title and body to string
            val title = binding.noteTitleEditText.text.toString()
            val body = binding.noteBodyEditText.text.toString()

            // if title not title and body present then save the note with it else if only title is give the save the note with empty body else show toast
            if (title.isNotEmpty() && body.isNotEmpty()) {
                val note = Note(
                    id = viewModel.notes.value?.size ?: 0,
                    title = title,
                    body = body
                )
                Log.d("CreatedNote", "onViewCreated:${note.id} ${note.title} ${note.body} ")
                viewModel.addNote(note)
                dismiss()
            } else {
                if(title.isNotEmpty()){
                    val note = Note(
                        id = viewModel.notes.value?.size ?: 0 ,
                        title = title,
                        body = ""
                    )
                    viewModel.addNote(note)
                    val getNote = viewModel.getNoteById(note.id)
                    Log.d("addnote", "onViewCreated:${getNote?.title} ")
                    dismiss()

                }
                else{
                    binding.noteTitleEditText.error = "Title cannot be empty"
                    Toast.makeText(context,"Title and body cannot be empty",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
