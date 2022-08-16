package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CustomAdapter
import com.example.myapplication.generator.ServiceGenerator
import com.example.myapplication.models.NewsResponse
import com.example.myapplication.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rvNews = findViewById<RecyclerView>(R.id.recyclerView)


        val serviceGenerator = ServiceGenerator.buildService(APIService::class.java)
        val call =
            serviceGenerator.getLatestNews(ServiceGenerator.QUERY_STRING, ServiceGenerator.API_KEY)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.body()?.status.equals(ServiceGenerator.STATUS_OK)) {
                    rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = response.body()?.let { it1 -> CustomAdapter(it1.articles) }

                        Log.d(ServiceGenerator.TAG_SUCCESS, response.body()?.articles.toString())
                    }
                }else{
                    Toast.makeText(this@MainActivity,"can't get data check you internet connection"
                        ,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"call failed check you internet connection"
                    ,Toast.LENGTH_LONG).show()
                Log.e(ServiceGenerator.TAG_Failure, t.message.toString())
            }

        })
    }
}