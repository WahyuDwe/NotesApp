package com.wahyudwi.notesapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wahyudwi.notesapp.data.NoteRepository
import com.wahyudwi.notesapp.data.entity.NoteEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NoteRepository(application)

    fun getAllNote(filter: String): LiveData<List<NoteEntity>> = repository.getAllNote(filter)

    fun getSearchNote(search: String): LiveData<List<NoteEntity>> = repository.getSearchNote(search)

}