package com.example.notesmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.notesmvvm.databinding.ActivityEditNoteBinding
import com.example.notesmvvm.ui.MainActivity
import com.example.notesmvvm.viewModel.NoteViewModel


class EditNotes : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var viewModel: NoteViewModel
    private var  noteId:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ActivityEditNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize the view
        initializing()




    }

    private fun initializing() {
        //set up binding
        viewModel = (activity as MainActivity).viewModel

        // get id from argument
        noteId = requireArguments().getInt("noteId")
        Log.d("receivedArgs", "onViewCreated:${noteId} ")
        val note = viewModel.getNoteById(noteId)
        // bind note
        binding.note = note
        //set up on click listener for save buttom
        binding.saveEditedNoteButton.setOnClickListener{
            val updatedTitle = binding.editNoteTitleEditText.text.toString()
            val updatedBody = binding.editNoteBodyEditText.text.toString()

            if (updatedTitle.isNotEmpty() && updatedBody.isNotEmpty()) {
                viewModel.updateNote(noteId, updatedTitle, updatedBody)
                dismiss()
            } else {
                if(updatedTitle.isNotEmpty()){
                    viewModel.updateNote(noteId,updatedTitle,"")
                    dismiss()

                }
                else{
                    Toast.makeText(context,"Cannot put title empty", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}