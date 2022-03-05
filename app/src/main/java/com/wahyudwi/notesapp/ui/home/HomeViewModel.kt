package com.wahyudwi.notesapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wahyudwi.notesapp.data.NoteRepository
import com.wahyudwi.notesapp.data.entity.NoteEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val getAllNote: LiveData<List<NoteEntity>>
    private val repository = NoteRepository(application)

    init {
        getAllNote = repository.getAllNote
    }

}