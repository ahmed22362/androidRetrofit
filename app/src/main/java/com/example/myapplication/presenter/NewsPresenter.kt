package com.example.myapplication.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.models.pojo.NewsResponse
import com.example.myapplication.models.repo.NewsRepo
import com.example.myapplication.utils.Constants
import com.example.myapplication.view.interfaces.NewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(newsView: NewsInterface.NewsView, context: Context) :
    NewsInterface.NewsPresenter {

    private val view: NewsInterface.NewsView = newsView
    private val model: NewsInterface.NewsModel = NewsRepo(context)

    override fun networkCall() {
        view.showProgressBar()
        model.getNews(call)
    }

    override fun getCachedData() {
        if (model.getCache() != null) {
            view.updateViewData(model.getCache()!!)
        }
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
        view.cacheError()
    }

    override fun uiAutoUpdate(articles: List<Article>) {
        view.updateViewData(articles)
    }

    private val call = object : Callback<NewsResponse> {
        override fun onResponse(
            call: Call<NewsResponse>,
            response: Response<NewsResponse>
        ) {
            view.hideProgressBar()
            if (response.body()?.status.equals(Constants.API.RESPONSE_OK)) {
                responseOk(response)
            } else {
                responseBad()
            }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            view.hideProgressBar()
            view.showError()
        }
    }

    fun responseOk(response: Response<NewsResponse>) {
        if (response.body()?.articles?.size!! > 0) {
            response.body()?.let { it.articles?.let { it1 -> view.updateViewData(it1) } }
            response.body()?.let { it.articles?.let { it1 -> model.saveInDB(it1) } }
        } else {
            view.noArticlesError()
        }
    }

    fun responseBad() {
        view.badResponseError()
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    fun getNews(context: Context) {
        if (checkForInternet(context)) {
            networkCall()
        } else {
            getCachedData()
        }
    }
}