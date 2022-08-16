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
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val newsImageView: ImageView = ItemView.findViewById(R.id.img_NewsImage)
        private val titleTextView: TextView = ItemView.findViewById(R.id.tv_title)
        private val authorTextView: TextView = ItemView.findViewById(R.id.tv_author)

        fun bind(article: Article){
            bindImage(article.urlToImage)
            bindTitle(article.title)
            bindAuthor(article.author)
        }
        private fun bindImage(url:String){
            Picasso.get().load(url).into(newsImageView)
        }
        private fun bindTitle(title:String){
            titleTextView.text = title
        }
        private fun bindAuthor(author:String){
            authorTextView.text = author
        }
    }
}

