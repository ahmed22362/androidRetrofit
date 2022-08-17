package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Articles")
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @SerializedName("title")
    var title : String ,

    @SerializedName("author")
    var author : String,

    @SerializedName("urlToImage")
    var urlToImage : String)
