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
    private val mList: List<Article>
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_design, parent, false)
        return NewsViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = mList[position]
        holder.bind(article)
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
            bindImage(article.urlToImage)
            bindTitle(article.title)
            bindAuthor(article.author)
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
                listener.onItemClicked(absoluteAdapterPosition)
            }
        }

    }


    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

}

