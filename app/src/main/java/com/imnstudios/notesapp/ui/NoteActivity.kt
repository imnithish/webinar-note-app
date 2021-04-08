package com.imnstudios.notesapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.imnstudios.notesapp.R
import com.imnstudios.notesapp.data.Note
import com.imnstudios.notesapp.data.NoteDao
import com.imnstudios.notesapp.data.NoteDatabase
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteActivity : AppCompatActivity() {

    lateinit var noteDao: NoteDao

    var note: Note? = null
    private var noteMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        NoteDatabase.getDatabase(applicationContext)?.let {
            noteDao = it.weightLogDao()
        }

        note = intent?.getSerializableExtra("note") as Note?
        note?.let {
            tile_edit_text?.setText(it.title)
            description_edit_text?.setText(it.description)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        this.noteMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when (note) {
            null -> this.noteMenu?.getItem(0)?.isVisible = false
            else -> this.noteMenu?.getItem(0)?.isVisible = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_note -> {
                MaterialAlertDialogBuilder(this@NoteActivity).setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.sure_to_delete))
                    .setPositiveButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setNegativeButton(getString(R.string.yes)) { dialog, _ ->
                        dialog.dismiss()
                        lifecycleScope.launch {
                            withContext(Dispatchers.IO) {
                                note?.let { noteDao.delete(it) }
                            }
                            startActivity(Intent(this@NoteActivity, HomeActivity::class.java))
                            finishAffinity()
                        }
                    }
                    .setCancelable(true)
                    .show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val titleString = tile_edit_text.text.toString()
        val descriptionString = description_edit_text.text.toString()
        if (titleString.isEmpty() && descriptionString.isEmpty())
            Toast.makeText(this, getString(R.string.empty_note_discarded), Toast.LENGTH_SHORT).show()
        else {
            // upsert into db
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {

                    note?.let {
                        noteDao.upsert(
                            Note(
                                title = titleString,
                                description = descriptionString,
                                id = it.id
                            )
                        )
                    } ?: run {
                        noteDao.upsert(
                            Note(
                                title = titleString,
                                description = descriptionString
                            )
                        )
                    }

                }
            }
        }
        super.onBackPressed()
    }
}