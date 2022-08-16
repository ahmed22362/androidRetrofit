package com.example.myapplication.presenter

import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.repo.NewsRepo
import com.example.myapplication.models.service.APIService
import com.example.myapplication.models.service.ServiceGenerator
import com.example.myapplication.view.interfaces.NewsInterface

class NewsPresenter(newsView: NewsInterface.NewsView):NewsInterface.NewsPresenter{

    private val view: NewsInterface.NewsView = newsView
    private val model: NewsInterface.NewsModel = NewsRepo()



    override fun networkCall() {
        model?.getNews(this)
    }

    override fun uiAutoUpdate(articles: List<Article>) {
        view.updateViewData(articles)
    }

}