package com.wahyudwi.notesapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.data.room.NoteDao
import com.wahyudwi.notesapp.data.room.NoteDatabase

class NoteRepository(application: Application) {
    private val noteDao: NoteDao

    init {
        val db: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = db.noteDao()
    }

    val getAllNote: LiveData<List<NoteEntity>> = noteDao.getAllNote()

    suspend fun addNote(note: NoteEntity) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }
}