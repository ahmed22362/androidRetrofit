package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Article
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Article>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_news_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mList[position]
        Picasso.get().load(article.urlToImage).into(holder.imageView)
        holder.titleTextView.text = article.title
        holder.authorTextView.text = article.author

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = ItemView.findViewById(R.id.imageview)
        val titleTextView: TextView = ItemView.findViewById(R.id.title_tv)
        val authorTextView: TextView = ItemView.findViewById(R.id.author_tv)
        //ahmed ezz todo create new method here called bind which take article as param and call it in the onBindViewHolder
        // and inside it call
//        Picasso.get().load(article.urlToImage).into(holder.imageView)
//        holder.titleTextView.text = article.title
//        holder.authorTextView.text = article.author
       // but each one of them in separate method for example bindTitle()
    }
}

