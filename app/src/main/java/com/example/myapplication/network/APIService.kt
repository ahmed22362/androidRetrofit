package com.example.myapplication.network

import com.example.myapplication.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("top-headlines")
    fun getLatestNews(@Query("sources")source:String ,@Query("apiKey") apiKey:String ):Call<NewsResponse>

}