package com.wahyudwi.notesapp.ui.content

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wahyudwi.notesapp.data.NoteRepository
import com.wahyudwi.notesapp.data.entity.NoteEntity
import kotlinx.coroutines.launch

class ContentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NoteRepository(application)

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}