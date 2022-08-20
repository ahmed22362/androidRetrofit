package com.example.myapplication.presenter

import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.repo.NewsRepo
import com.example.myapplication.view.interfaces.NewsInterface

class NewsPresenter(newsView: NewsInterface.NewsView):NewsInterface.NewsPresenter{

    private val view: NewsInterface.NewsView = newsView
    private val model: NewsInterface.NewsModel = NewsRepo()



    override fun networkCall() {
        model.getNews(this)
    }

    override fun getCashedData() {
        model.makeCash(this)
    }

    override fun loading() {
        view.showProgressBar()
    }

    override fun doneLoading() {
        view.hideProgressBar()
    }

    override fun showError() {
        view.showError()
    }

    override fun cashError() {
        view.cashError()
    }

    override fun uiAutoUpdate(articles: List<Article>) {
        view.updateViewData(articles)
    }
}