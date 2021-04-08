package com.imnstudios.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun weightLogDao(): NoteDao

    companion object {
        var instance: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java,
                    "NotesAppDatabase"
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}