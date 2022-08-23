package com.example.myapplication

object Constants {
    const val TAG = "TAG"

    object API {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "f45d64710a2641d3a62beaa221604408"
        const val SOURCE = "techcrunch"
        const val RESPONSE_OK = "ok"
    }

    object DATABASE {
        const val DATABASE_NAME = "Articles.db"
        const val ARTICLES_TABLE_NAME = "Articles"
    }

    object EXTRA {
        const val ARTICLE_EXTRA = "main"
    }
}