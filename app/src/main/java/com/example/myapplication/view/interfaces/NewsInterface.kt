package com.example.myapplication.view.interfaces

import com.example.myapplication.models.POJO.Article

interface NewsInterface {
    interface NewsModel {
        fun getNews(presenter: NewsPresenter)

    }

    interface NewsView {
        fun updateViewData(articles: List<Article>)
    }

    interface NewsPresenter {
        fun networkCall()

        fun uiAutoUpdate(articles:List<Article>)
    }
}
