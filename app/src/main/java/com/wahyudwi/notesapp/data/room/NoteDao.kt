package com.wahyudwi.notesapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.wahyudwi.notesapp.data.entity.NoteEntity

@Dao
interface NoteDao {
    @RawQuery(observedEntities = [NoteEntity::class])
    fun getAllNote(query: SupportSQLiteQuery): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}