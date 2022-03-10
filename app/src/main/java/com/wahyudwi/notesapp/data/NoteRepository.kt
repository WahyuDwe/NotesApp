package com.wahyudwi.notesapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.data.room.NoteDao
import com.wahyudwi.notesapp.data.room.NoteDatabase
import com.wahyudwi.notesapp.utils.SortUtils

class NoteRepository(application: Application) {
    private val noteDao: NoteDao

    init {
        val db: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = db.noteDao()
    }

    fun getAllNote(filter: String): LiveData<List<NoteEntity>> {
        val query = SortUtils.sortedQuery(filter)
        return noteDao.getAllNote(query)
    }

    fun getSearchNote(search: String): LiveData<List<NoteEntity>> = noteDao.searchNote(search)


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