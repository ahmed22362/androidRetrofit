package com.example.myapplication.models.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.POJO.NewsResponse
import com.example.myapplication.models.database.AppDatabase
import com.example.myapplication.models.database.DatabaseGenerator
import com.example.myapplication.models.service.APIService
import com.example.myapplication.models.service.ServiceGenerator
import com.example.myapplication.view.interfaces.Constants
import com.example.myapplication.view.interfaces.NewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo : NewsInterface.NewsModel {


    private var application: APIService? = null
    private var appDatabase: AppDatabase? = DatabaseGenerator.INSTANCE

    init {
        application = ServiceGenerator.buildService(APIService::class.java)
    }

    override fun getNews(presenter: NewsInterface.NewsPresenter) {
        presenter.loading()
        application?.getLatestNews("techcrunch", Constants.API_KEY)
            ?.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.body()?.status.equals("ok")) {
                        presenter.doneLoading()
                        response.body()?.let { presenter.uiAutoUpdate(it.articles) }
                        response.body()?.let { saveInDB(it.articles) }
                    } else {
                        presenter.showError()
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    presenter.doneLoading()
                    presenter.showError()
                }
            })
    }

    override fun makeCash(presenter: NewsInterface.NewsPresenter) {
        val articles = appDatabase?.getArticlesDao()?.getAllArticles()
        if (articles != null) {
            presenter.uiAutoUpdate(articles)
        } else {
            presenter.cashError()

        }
    }

    fun saveInDB(articles: List<Article>) {
        appDatabase?.getArticlesDao()?.deleteAll()
        appDatabase?.getArticlesDao()?.insertArticles(articles)
    }


}