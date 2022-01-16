package com.example.areej.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TVShowsDao {
    @Query("SELECT * FROM tv_shows")
    fun getTVShows(): LiveData<List<TvShowsDB>>

    @Insert
    fun addShow(tvShows: TvShowsDB)

    @Delete
    fun deleteShow(tvShows: TvShowsDB)
}