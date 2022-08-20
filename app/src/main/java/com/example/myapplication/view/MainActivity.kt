package com.example.myapplication.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomAdapter
import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.database.AppDatabase
import com.example.myapplication.models.database.DatabaseGenerator
import com.example.myapplication.presenter.NewsPresenter
import com.example.myapplication.view.interfaces.NewsInterface

class MainActivity : AppCompatActivity(), NewsInterface.NewsView {

    private var newRV: RecyclerView? = null
    private var articles: List<Article>? = null
    private var presenter: NewsPresenter? = null
    private var progressBar: ProgressBar? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database: AppDatabase = DatabaseGenerator().getInstance(this)

        Log.e("path", getDatabasePath("articles.db").absolutePath.toString())

        initialize()

        if (checkForInternet(this@MainActivity)) {
            presenter?.networkCall()
            articles?.let { updateViewData(it) }
        } else {
            Toast.makeText(this, "there are no internet connection", Toast.LENGTH_LONG).show()
            presenter?.uiAutoUpdate(database.getArticlesDao().getAllArticles())
        }
    }

    override fun updateViewData(articles: List<Article>) {
        var adapter = CustomAdapter(articles)
        newRV?.layoutManager = LinearLayoutManager(this@MainActivity)
        newRV?.adapter = adapter

        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener {
            override fun onItemClicked(position: Int) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("main", articles[position])
                startActivity(intent)
            }
        })
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
        Toast.makeText(this, "something happened try again later", Toast.LENGTH_LONG).show()
    }

    override fun cashError() {
        Toast.makeText(this, "can't cash data check your storage", Toast.LENGTH_LONG).show()
    }


    private fun initialize() {
        progressBar = findViewById(R.id.main_progressBar)
        newRV = findViewById(R.id.recyclerView)
        presenter = NewsPresenter(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}