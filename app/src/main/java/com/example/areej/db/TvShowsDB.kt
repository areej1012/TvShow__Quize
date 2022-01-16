package com.example.areej.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
class TvShowsDB(@PrimaryKey(autoGenerate = true) val pk : Int? , val title : String ,val summary : String?,  val language : String , val image : String? = "") {
}