package com.imnstudios.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(note: Note): Long

    @Delete
    fun delete(note: Note)

//    @Query("select * from notes_table ORDER BY id DESC")
//    fun getAllNotes(): LiveData<List<Note>>

    @Query("select * from notes_table")
    fun getAllNotes(): LiveData<List<Note>>
}