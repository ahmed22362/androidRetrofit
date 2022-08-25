package com.example.myapplication.view.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.Constants
import com.example.myapplication.R
import com.example.myapplication.utils.Utils
import com.example.myapplication.view.news.adapters.NewsAdapter
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.presenter.NewsPresenter
import com.example.myapplication.view.news_details.NewsDetailsActivity
import com.example.myapplication.view.interfaces.NewsInterface

class NewsActivity : AppCompatActivity(), NewsInterface.NewsView {

    private var newsRecyclerView: RecyclerView? = null
    private var newsPresenter: NewsPresenter? = null
    private var progressBar: ProgressBar? = null
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViewData()

        newsPresenter?.getNews(this)

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
        Utils.toast(R.string.show_error, this)
    }

    override fun cacheError() {
        Utils.toast(R.string.cash_error, this)
    }

    override fun noArticlesError() {
        Utils.toast(R.string.no_articles, this)
    }

    override fun badResponseError() {
        Utils.toast(R.string.bad_response_error, this)
    }

    private fun initializeViewData() {
        initializePresenter()
        initializeViews()
    }

    private val onItemClickListener = object : NewsAdapter.OnItemClickListener {
        override fun onItemClicked(article: Article) {
            val intent = Intent(this@NewsActivity, NewsDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA.ARTICLE_EXTRA, article)
            startActivity(intent)
        }
    }

    private fun initializeViews() {
        adapter = NewsAdapter(arrayListOf(), onItemClickListener)
        progressBar = findViewById(R.id.news_progressBar)
        newsRecyclerView = findViewById(R.id.news_recyclerView)
    }

    private fun initializePresenter() {
        newsPresenter = NewsPresenter(this, this)
    }
}