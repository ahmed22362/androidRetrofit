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

    private var detailsToolBarImage: ImageView? = null
    private var detailsCollapsingToolbar: CollapsingToolbarLayout? = null
    private var detailsDescriptionTv: TextView? = null
    private var detailsOpenBrowserBtn: Button? = null
    private var detailsToolbar: Toolbar? = null
    private var detailsTitleTv: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeViews()

        setSupportActionBar(detailsToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }


        val article = intent.getParcelableExtra<Article>(Constants.EXTRA.ARTICLE_EXTRA)


        if (article != null) {
            detailsToolBarImage?.let { bindArticleData(article, it) }
        } else {
            Utils.toast(R.string.no_article_received, this)
        }

        detailsOpenBrowserBtn?.setOnClickListener {
            onBrowserButtonClick(article)
        }

        detailsToolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    private fun bindArticleData(article: Article, imageView: ImageView) {
        bindImage(article.urlToImage, imageView)
        bindTitle(article.title)
        bindDescription(article.description)
    }

    private fun bindImage(url: String?, imageView: ImageView) {
        Utils.loadImage(imageView, url)
    }

    private fun bindTitle(title: String?) {
        detailsCollapsingToolbar?.title = title ?: ""
        detailsTitleTv?.text = title ?: ""
    }

    private fun bindDescription(description: String?) {
        detailsDescriptionTv?.text = description ?: ""
    }

    private fun initializeViews() {
        detailsToolBarImage = findViewById(R.id.details_toolBar_image)
        detailsCollapsingToolbar = findViewById(R.id.collapsingToolbar)
        detailsDescriptionTv = findViewById(R.id.details_description_tv)
        detailsOpenBrowserBtn = findViewById(R.id.details_open_browser_btn)
        detailsToolbar = findViewById(R.id.details_toolbar)
        detailsTitleTv = findViewById(R.id.details_title_tv)
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