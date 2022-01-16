package com.example.areej.api

import com.example.areej.tvshow.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AIPInterface {
    @GET("shows?")
    fun getShows(@Query("q") show: String ): Call<TvShow>
}