package com.example.areej.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var retrofit : Retrofit? = null

    fun tvShows() : Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}