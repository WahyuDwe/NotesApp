package com.wahyudwi.notesapp.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wahyudwi.notesapp.data.NoteRepository
import com.wahyudwi.notesapp.data.entity.NoteEntity
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NoteRepository(application)

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }
}