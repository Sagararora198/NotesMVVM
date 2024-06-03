package com.example.notesmvvm.ui

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesmvvm.ui.fragments.AddNoteFragment
import com.example.notesmvvm.ui.fragments.EditNotes
import com.example.notesmvvm.R
import com.example.notesmvvm.adapter.NotesAdapter
import com.example.notesmvvm.databinding.ActivityMainBinding
import com.example.notesmvvm.viewModel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val viewModel:NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize layout and other binding
        initializeBinding()

    }


    private fun initializeBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // setting up the recycler view

        val adapter = NotesAdapter(viewModel.notes.value?: emptyList()){
                note-> openEditNoteFragment(note.id)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        viewModel.notes.observe(this){
            adapter.updateNotes(it)
        }

        // initializing the onclick events
        binding.addNote.setOnClickListener{
            val addNoteFragment = AddNoteFragment()
            addNoteFragment.show(supportFragmentManager, "AddNoteFragment")
        }

        // search button functionality
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(!p0.isNullOrBlank()){
                    val filteredList = viewModel.notes.value?.filter { note-> note.title.contains(p0, ignoreCase = true) || note.body.contains(p0
                        , ignoreCase = true) }
                    adapter.updateNotes(filteredList?: emptyList())

                }
                else{
                    adapter.updateNotes(viewModel.notes.value?: emptyList())
                }
                return true
            }
        })

    }


    private fun openEditNoteFragment(id: Int) {
        val editNoteFragment = EditNotes().apply {
            arguments = Bundle().apply {
                putInt("noteId",id)

            }

        }
        editNoteFragment.show(supportFragmentManager,"EditNoteFragment")


    }
}