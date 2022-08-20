package com.example.myapplication.view.interfaces

import com.example.myapplication.models.POJO.Article

interface NewsInterface {
    interface NewsModel {
        fun getNews(presenter: NewsPresenter)
        fun makeCash(presenter: NewsPresenter)
    }

    interface NewsView {
        fun updateViewData(articles: List<Article>)

        fun showProgressBar()
        fun hideProgressBar()

        fun showError()
        fun cashError()
    }

    interface NewsPresenter {
        fun networkCall()

        fun uiAutoUpdate(articles: List<Article>)

        fun getCashedData()

        fun loading()
        fun doneLoading()

        fun showError()
        fun cashError()
    }
}
