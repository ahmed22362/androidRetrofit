package com.example.myapplication.models.repo

import android.content.Context
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.models.pojo.NewsResponse
import com.example.myapplication.models.database.AppDatabase
import com.example.myapplication.models.database.DatabaseGenerator
import com.example.myapplication.models.service.APIService
import com.example.myapplication.models.service.ServiceGenerator
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.Utils
import com.example.myapplication.view.interfaces.NewsInterface
import retrofit2.Callback

class NewsRepo(context: Context) : NewsInterface.NewsModel {

    private var _apiService: APIService? = null
    private var appDatabase: AppDatabase? = DatabaseGenerator().getInstance(context)

    init {
        _apiService = ServiceGenerator.buildService(APIService::class.java)
    }

    override fun getNews(call: Callback<NewsResponse>) {
        _apiService?.getLatestNews(Constants.API.SOURCE, Constants.API.API_KEY)
            ?.enqueue(call)
    }

    override fun getCache(): List<Article>? {
        return appDatabase?.getArticlesDao()?.getAllArticles()
    }

    override fun saveInDB(articles: List<Article>) {
        if (appDatabase?.getArticlesDao()?.getAllArticles()?.isNotEmpty() == true) {
            appDatabase?.getArticlesDao()?.deleteAll()
        }
        Utils.log(appDatabase.toString() + " saved in data")
        appDatabase?.getArticlesDao()?.insertArticles(articles)
    }


}