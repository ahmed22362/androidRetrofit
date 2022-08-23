package com.example.myapplication.view.interfaces

import android.content.Context
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.models.pojo.NewsResponse
import retrofit2.Callback

interface NewsInterface {
    interface NewsModel {
        fun getNews(call: Callback<NewsResponse>)
        fun getCache(): List<Article>?
        fun saveInDB(articles: List<Article>)
    }

    interface NewsView {
        fun updateViewData(articles: List<Article>)

        fun showProgressBar()
        fun hideProgressBar()

        fun showError()
        fun cacheError()
    }

    interface NewsPresenter {
        fun networkCall()

        fun uiAutoUpdate(articles: List<Article>)

        fun getCachedData()

        fun loading()
        fun doneLoading()

        fun showError()
        fun cashError()
    }
}
