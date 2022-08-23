package com.example.myapplication.models.pojo

data class NewsResponse(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: ArrayList<Article>? = null
)