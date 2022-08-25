package com.example.myapplication.view.news_details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.utils.Constants
import com.example.myapplication.R
import com.example.myapplication.utils.Utils
import com.example.myapplication.models.pojo.Article
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NewsDetailsActivity : AppCompatActivity() {

    private var detailsToolBarImage: ImageView? = null
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var detailsDescriptionTv: TextView? = null
    private var detailsFAB: FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide();

        initializeViews()

        val article = intent.getParcelableExtra<Article>(Constants.EXTRA.ARTICLE_EXTRA)


        if (article != null) {
            detailsToolBarImage?.let { bindArticleData(article, it) }
        } else {
            Utils.toast(R.string.no_article_received, this)
        }

        detailsFAB?.setOnClickListener {
            if (article?.url != null) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                startActivity(intent)
            } else {
                Utils.toast(R.string.no_article_received, this)
            }
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
        collapsingToolbar?.title = title ?: ""
    }

    private fun bindDescription(description: String?) {
        detailsDescriptionTv?.text = description ?: ""
    }

    private fun initializeViews() {
        detailsToolBarImage = findViewById(R.id.details_toolBar_image)
        collapsingToolbar = findViewById(R.id.collapsingToolbar)
        detailsDescriptionTv = findViewById(R.id.details_description_tv)
        detailsFAB = findViewById(R.id.details_fab)
    }

}