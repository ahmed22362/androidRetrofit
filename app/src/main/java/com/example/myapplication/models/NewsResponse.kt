package com.example.myapplication.models

data class NewsResponse(var status: String,var totalResults:Int,var articles: List<Article>)
//ahmed ezz todo as u use Gson use the serialize name annotation
