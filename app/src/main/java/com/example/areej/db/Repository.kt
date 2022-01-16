package com.example.areej.db

import androidx.lifecycle.LiveData

class Repository(private val tvShowsDao: TVShowsDao) {

    val getShows: LiveData<List<TvShowsDB>> = tvShowsDao.getTVShows()

    suspend fun add(tvShows: TvShowsDB){
        tvShowsDao.addShow(tvShows)
    }

    suspend fun delete(tvShows: TvShowsDB){
        tvShowsDao.deleteShow(tvShows)
    }
}