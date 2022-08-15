package com.example.myapplication.generator

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private const val base_url: String = "https://newsapi.org/v2/"


    private val client= OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    fun<T> buildService(service:Class<T>): T {
        return retrofit.create(service)
    }
}