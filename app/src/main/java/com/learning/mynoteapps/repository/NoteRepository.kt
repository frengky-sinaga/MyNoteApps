package com.learning.mynoteapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.learning.mynoteapps.db.Note
import com.learning.mynoteapps.db.NoteDao
import com.learning.mynoteapps.db.NoteRoomDb
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDb.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes(): DataSource.Factory<Int, Note> = mNotesDao.getAllNotes()

    fun insert(note: Note) = executorService.execute { mNotesDao.insert(note) }

    fun delete(note: Note) = executorService.execute { mNotesDao.delete(note) }

    fun update(note: Note) = executorService.execute { mNotesDao.update(note) }
}