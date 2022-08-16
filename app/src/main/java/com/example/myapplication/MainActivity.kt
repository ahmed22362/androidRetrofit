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
    // ahmed ezz todo general comment format all files(classes and xml)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apikey = "f45d64710a2641d3a62beaa221604408" // ahmed ezz todo move this to the service generator and make it const val

        val btn = findViewById<Button>(R.id.main_btn) //  ahmed ezz todo why u create button and after its click u call the api, delete it and call the api directly
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        //  ahmed ezz todo either the view id or its object should have descriptive name for example
        // rvNews - recyclerViewNews -rv_News use any naming convention and apply it acoss the app


        val serviceGenerator = ServiceGenerator.buildService(APIService::class.java)
        // ahmed ezz todo move techcrunch to the service generator and make it const val

        val call = serviceGenerator.getLatestNews("techcrunch", apikey)

        btn.setOnClickListener {
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    // ahmed ezz todo move ok to the service generator and make it const val
                    if (response.body()?.status.equals("ok")) {
                        rv.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = response.body()?.let { it1 -> CustomAdapter(it1.articles) }

                            // ahmed ezz todo unify tag of log here and add it in const val value
                            Log.d("Success", response.body()?.articles.toString())
                        }
                    } // ahmed ezz todo what if the status not ok, you should display appropratiate error message to the user
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("failed", t.message.toString())
                 // ahmed ezz todo what if onFailure happened, you should display appropratiate error message to the user

            }

            })
        }
    }
}