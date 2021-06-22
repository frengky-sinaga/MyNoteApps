package com.learning.mynoteapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDb? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDb {
            if (INSTANCE == null) {
                synchronized(NoteRoomDb::class.java) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            NoteRoomDb::class.java,
                            "note_database"
                        )
                        .build()
                }
            }
            return INSTANCE as NoteRoomDb
        }
    }
}