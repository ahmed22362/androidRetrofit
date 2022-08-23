package com.example.myapplication.models.service

import com.example.myapplication.models.pojo.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("top-headlines")
    fun getLatestNews(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

}