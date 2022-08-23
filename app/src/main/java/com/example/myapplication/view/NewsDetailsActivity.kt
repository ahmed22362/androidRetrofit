package com.example.myapplication.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.Constants
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.models.pojo.Article
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide();

        val article = intent.getParcelableExtra<Article>(Constants.EXTRA.ARTICLE_EXTRA)

        val detailsToolBarImage = findViewById<ImageView>(R.id.details_toolBar_image)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        val detailsDescriptionTv = findViewById<TextView>(R.id.details_description_tv)
        val detailsFAB = findViewById<FloatingActionButton>(R.id.details_fab)

        var intent: Intent? = null

        if (article != null) {
            collapsingToolbar.title = article.title
            Utils.loadImage(detailsToolBarImage, article.urlToImage)
            detailsDescriptionTv.text = article.content
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        } else {
            Utils.toast("there are no data", this)
        }

        detailsFAB.setOnClickListener {
            if (intent != null) {
                startActivity(intent)
            } else {
                Utils.toast("Can't open because there are no data", this)
            }
        }
    }
}