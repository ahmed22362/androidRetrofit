package com.example.myapplication.generator

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    const val API_KEY = "f45d64710a2641d3a62beaa221604408"
    const val QUERY_STRING = "techcrunch"
    const val STATUS_OK = "ok"
    const val TAG_SUCCESS ="Success"
    const val TAG_Failure ="Failure"

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