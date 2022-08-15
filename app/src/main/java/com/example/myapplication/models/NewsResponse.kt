package com.example.myapplication.models

data class NewsResponse(var status: String,var totalResults:Int,var articles: List<Article>)