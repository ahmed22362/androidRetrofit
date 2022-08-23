package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Constants
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.adapters.NewsAdapter
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.presenter.NewsPresenter
import com.example.myapplication.view.interfaces.NewsInterface

class NewsActivity : AppCompatActivity(), NewsInterface.NewsView {

    private var newRV: RecyclerView? = null
    private var newsPresenter: NewsPresenter? = null
    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        if (newsPresenter?.checkForInternet(this@NewsActivity) == true) {
            newsPresenter?.networkCall()
        } else {
            Utils.toast("there are no internet connection", this)
            newsPresenter?.getCachedData()
        }
    }

    override fun updateViewData(articles: List<Article>) {
        var adapter = NewsAdapter(articles)
        newRV?.layoutManager = LinearLayoutManager(this@NewsActivity)
        newRV?.adapter = adapter

        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClicked(position: Int) {
                val intent = Intent(this@NewsActivity, NewsDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA.ARTICLE_EXTRA, articles[position])
                startActivity(intent)
            }
        })
    }


    override fun showProgressBar() {
        if (progressBar != null) {
            progressBar!!.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (progressBar != null) {
            progressBar!!.visibility = View.GONE
        }
    }

    override fun showError() {
        Utils.toast("something happened try again later", this)
    }

    override fun cacheError() {
        Utils.toast("can't cash data check your storage", this)
    }


    private fun initialize() {
        progressBar = findViewById(R.id.news_progressBar)
        newRV = findViewById(R.id.news_recyclerView)
        newsPresenter = NewsPresenter(this, this)
    }

}