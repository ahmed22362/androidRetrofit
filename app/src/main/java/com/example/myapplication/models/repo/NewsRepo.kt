package com.example.myapplication.models.repo

import android.util.Log
import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.POJO.NewsResponse
import com.example.myapplication.models.service.APIService
import com.example.myapplication.models.service.ServiceGenerator
import com.example.myapplication.view.interfaces.Constants
import com.example.myapplication.view.interfaces.NewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo : NewsInterface.NewsModel {

    private var application: APIService? = null

    init {
        application = ServiceGenerator.buildService(APIService::class.java)
    }

    override fun getNews(presenter: NewsInterface.NewsPresenter){
        application?.getLatestNews("techcrunch", Constants.API_KEY)
            ?.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.body()?.status.equals("ok")) {
                        Log.d("Success", response.body()?.articles.toString())
                        response.body()?.let { presenter.uiAutoUpdate(it.articles) }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.d("failure", t.toString())
                }

            })


    }
}