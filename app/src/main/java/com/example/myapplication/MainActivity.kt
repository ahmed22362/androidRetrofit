package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

        val apikey = "f45d64710a2641d3a62beaa221604408"

        val btn = findViewById<Button>(R.id.main_btn)
        val rv = findViewById<RecyclerView>(R.id.recyclerView)


        val serviceGenerator = ServiceGenerator.buildService(APIService::class.java)
        val call = serviceGenerator.getLatestNews("techcrunch", apikey)

        btn.setOnClickListener {
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.body()?.status.equals("ok")) {
                        rv.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = response.body()?.let { it1 -> CustomAdapter(it1.articles) }

                            Log.d("Success", response.body()?.articles.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("failed", t.message.toString())
                }

            })
        }
    }
}