package com.imnstudios.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        var noteDatabase: NoteDatabase? = null

        // to be synchronized
        fun getDatabase(context: Context): NoteDatabase? {
            if (noteDatabase == null) {
                noteDatabase = Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java,
                    "NotesAppDatabase"
                ).fallbackToDestructiveMigration().build()
            }
            return noteDatabase
        }
    }
}