package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomAdapter
import com.example.myapplication.models.POJO.Article
import com.example.myapplication.models.service.ServiceGenerator
import com.example.myapplication.models.POJO.NewsResponse
import com.example.myapplication.models.service.APIService
import com.example.myapplication.presenter.NewsPresenter
import com.example.myapplication.view.interfaces.Constants
import com.example.myapplication.view.interfaces.NewsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() ,NewsInterface.NewsView{

    private var showBTN: Button? = null
    private var newRV : RecyclerView? = null
    private var articles:List<Article>? = null
    private var presenter: NewsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        showBTN?.setOnClickListener{
        presenter?.networkCall()
        articles?.let { updateViewData(it) }
        }
    }
    override fun updateViewData(articles: List<Article>) {

        newRV?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(articles)
        }
    }


    fun  initialize(){
        showBTN = findViewById(R.id.main_btn)
        newRV = findViewById(R.id.recyclerView)
        presenter = NewsPresenter(this)
    }
}