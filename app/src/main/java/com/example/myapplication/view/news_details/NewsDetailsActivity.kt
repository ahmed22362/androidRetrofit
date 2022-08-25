package com.example.myapplication.view.news_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.Utils
import com.google.android.material.appbar.CollapsingToolbarLayout

class NewsDetailsActivity : AppCompatActivity() {

    private var imageToolbarDetails: ImageView? = null
    private var collapsingToolbarDetails: CollapsingToolbarLayout? = null
    private var tvDescriptionDetails: TextView? = null
    private var btnOpenBrowserDetails: Button? = null
    private var toolbarDetails: Toolbar? = null
    private var tvTitleDetails: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeViews()

        setSupportActionBar(toolbarDetails)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val article = intent.getParcelableExtra<Article>(Constants.EXTRA.ARTICLE_EXTRA)

        if (article != null) {
            bindArticleData(article)
        } else {
            Utils.toast(R.string.no_article_received, this)
        }

        btnOpenBrowserDetails?.setOnClickListener {
            onBrowserButtonClick(article)
        }

        toolbarDetails?.setNavigationOnClickListener {
            finish()
        }
    }

    private fun bindArticleData(article: Article) {
        bindImage(article.urlToImage)
        bindTitle(article.title)
        bindDescription(article.description)
    }

    private fun bindImage(url: String?) {
        if (imageToolbarDetails != null) {
            imageToolbarDetails?.let { Utils.loadImage(it, url) }
        } else {
            Utils.log("image view is null")
        }
    }

    private fun bindTitle(title: String?) {
        collapsingToolbarDetails?.title = title ?: ""
        tvTitleDetails?.text = title ?: ""
    }

    private fun bindDescription(description: String?) {
        tvDescriptionDetails?.text = description ?: ""
    }

    private fun initializeViews() {
        imageToolbarDetails = findViewById(R.id.iv_toolBar_details)
        collapsingToolbarDetails = findViewById(R.id.collapsingToolbar_details)
        tvDescriptionDetails = findViewById(R.id.tv_description_details)
        btnOpenBrowserDetails = findViewById(R.id.btn_open_browser_details)
        toolbarDetails = findViewById(R.id.toolbar_details)
        tvTitleDetails = findViewById(R.id.tv_title_details)
    }

    private fun onBrowserButtonClick(article: Article?) {
        if (article?.url != null) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(intent)
        } else {
            Utils.toast(R.string.no_article_received, this)
        }
    }
}