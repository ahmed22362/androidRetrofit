package com.example.myapplication.models.service

import com.example.myapplication.models.pojo.NewsResponse
import com.example.myapplication.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(Constants.API.TOP_HEADLINE_ENDPOINT)
    fun getLatestNews(
        @Query(Constants.API.QUERY_SOURCES) source: String,
        @Query(Constants.API.QUERY_APIKEY) apiKey: String
    ): Call<NewsResponse>

}