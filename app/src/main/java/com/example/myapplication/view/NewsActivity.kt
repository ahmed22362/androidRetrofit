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

    private var newsRecyclerView: RecyclerView? = null
    private var newsPresenter: NewsPresenter? = null
    private var progressBar: ProgressBar? = null
    private lateinit var adapter: NewsAdapter

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
        adapter.addNews(articles)
        newsRecyclerView?.layoutManager = LinearLayoutManager(this@NewsActivity)
        newsRecyclerView?.adapter = adapter

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

        adapter = NewsAdapter(arrayListOf(), onItemClickListener)
        progressBar = findViewById(R.id.news_progressBar)
        newsRecyclerView = findViewById(R.id.news_recyclerView)
        newsPresenter = NewsPresenter(this, this)
    }

    private val onItemClickListener = object : NewsAdapter.OnItemClickListener {
        override fun onItemClicked(article: Article) {
            val intent = Intent(this@NewsActivity, NewsDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA.ARTICLE_EXTRA, article)
            startActivity(intent)
        }
    }
}