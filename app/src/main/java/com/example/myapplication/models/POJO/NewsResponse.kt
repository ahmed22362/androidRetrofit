package com.example.myapplication.models.POJO

data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<Article>)