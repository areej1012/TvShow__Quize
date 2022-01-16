package com.example.areej.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.areej.db.Repository
import com.example.areej.db.ShowsDatabase
import com.example.areej.db.TvShowsDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    private val tvShows: LiveData<List<TvShowsDB>>

    init {
        val dao = ShowsDatabase.getDatabase(application).tvShowsDao()
        repository = Repository(dao)
        tvShows = repository.getShows
    }


    fun getTVShow(): LiveData<List<TvShowsDB>> {
        return tvShows
    }

    fun deleteTVShow(tvShow: TvShowsDB) {
        CoroutineScope(IO).launch {
            repository.delete(tvShow)
        }
    }
}