package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class Article (
    @SerializedName("title")
    var title : String ,

    @SerializedName("author")
    var author : String,

    @SerializedName("urlToImage")
    var urlToImage : String)
