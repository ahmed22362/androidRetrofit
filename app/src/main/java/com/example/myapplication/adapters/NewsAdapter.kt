package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.models.pojo.Article

class NewsAdapter(
    private var mList: List<Article>,
    private var mListener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_design, parent, false)
        return NewsViewHolder(view, mListener!!)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = mList[position]
        holder.bind(article)
    }

    fun addNews(articles: List<Article>){
        mList = articles
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NewsViewHolder(ItemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(ItemView) {

        private val imgNewsImage: ImageView = ItemView.findViewById(R.id.img_NewsImage)
        private val tvTitle: TextView = ItemView.findViewById(R.id.tv_title)
        private val tvAuthor: TextView = ItemView.findViewById(R.id.tv_author)
        fun bind(article: Article) {
            article.urlToImage?.let { bindImage(it) }
            article.title?.let { bindTitle(it) }
            article.author?.let { bindAuthor(it) }
        }

        private fun bindImage(url: String) {
            Utils.loadImage(imgNewsImage, url)
        }

        private fun bindTitle(title: String) {
            tvTitle.text = title
        }

        private fun bindAuthor(author: String) {
            tvAuthor.text = author
        }

        init {
            itemView.setOnClickListener {
                if (mListener != null)
                    listener.onItemClicked(mList[absoluteAdapterPosition])
            }
        }

    }


    interface OnItemClickListener {
        fun onItemClicked(article: Article)
    }

}

