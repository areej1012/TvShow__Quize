package com.example.areej.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [TvShowsDB::class],version = 3, exportSchema = false)
abstract class ShowsDatabase : RoomDatabase() {
    abstract fun tvShowsDao(): TVShowsDao

    companion object {
        @Volatile
        private var INSTANCE: ShowsDatabase? = null

        fun getDatabase(context: Context): ShowsDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowsDatabase::class.java,
                    "tvShows"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}