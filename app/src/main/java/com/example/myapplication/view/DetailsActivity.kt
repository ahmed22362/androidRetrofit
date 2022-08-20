package com.example.myapplication.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.models.POJO.Article
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide();

        val article =  intent.getParcelableExtra<Article>("main")

        val detailsImage = findViewById<ImageView>(R.id.details_toolBar_image)
        val detailsToolbarTitle = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        val detailsDisc = findViewById<TextView>(R.id.details_description_tv)
        val fab = findViewById<FloatingActionButton>(R.id.details_fab)


        detailsToolbarTitle.title = article?.title
        Picasso.get().load(article?.urlToImage).into(detailsImage)
        detailsDisc.text = article?.content

        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
            startActivity(intent)
        }
    }
}