package com.imnstudios.notesapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.imnstudios.notesapp.data.Note
import com.imnstudios.notesapp.R
import com.imnstudios.notesapp.data.NoteDao
import com.imnstudios.notesapp.data.NoteDatabase
import kotlinx.android.synthetic.main.activity_home.*


object DummyList {
    val dummyNotes = listOf(
        Note(title = "Hi", description = "How are you", id = 47),
        Note(title = "Hello", description = "Are you fine", id = 74)
    )
}

class HomeActivity : AppCompatActivity(), NotesAdapter.NoteClick {

    private var homeMenu: Menu? = null
    lateinit var sharedPreferences: SharedPreferences

    lateinit var noteAdapter: NotesAdapter

    lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * MODE_PRIVATE means that only the application creating the shared preference can read write the preference

        MODE_WORLD_READABLE means that other application can also read these preference using the shared preference API but can not wright value in this preference file

        MODE_WORLD_WRITEABLE means that other application can also read and write in the preference file using the shared preference API
         */
        sharedPreferences =
            applicationContext.getSharedPreferences("userPreferences", 0)
        setAppTheme()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        NoteDatabase.getDatabase(applicationContext)?.let {
            noteDao = it.weightLogDao()
        }

        noteDao.getAllNotes().observe(this, {

            if (it.isNullOrEmpty()) {
                empty.visibility = View.VISIBLE
            } else {
                noteAdapter = NotesAdapter(it.reversed(), this)
                notes_list.apply {
                    adapter = noteAdapter
                    layoutManager = LinearLayoutManager(this@HomeActivity)
                }
                empty.visibility = View.GONE
            }
        })

        new_note_button.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NoteActivity::class.java))
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        this.homeMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.homeMenu?.getItem(0)?.isChecked = sharedPreferences.getBoolean("dark_mode", true)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dark_mode_toggle -> {
                if (item.isChecked) {
                    sharedPreferences.edit().putBoolean("dark_mode", false).apply()
                    item.isChecked = false
                    setAppTheme()
                    super.recreate()

                } else {
                    sharedPreferences.edit().putBoolean("dark_mode", true).apply()
                    item.isChecked = true
                    setAppTheme()
                    super.recreate()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAppTheme() {
        when (sharedPreferences.getBoolean("dark_mode", true)) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onNoteClicked(position: Int) {
        val clickedItem: Note = noteAdapter.getItemAt(position)
        Intent(this@HomeActivity, NoteActivity::class.java).also {
            it.putExtra("note", clickedItem)
            startActivity(it)
        }
    }
}