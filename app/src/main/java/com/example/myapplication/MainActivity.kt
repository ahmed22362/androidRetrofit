package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CustomAdapter
import com.example.myapplication.generator.DatabaseGenerator
import com.example.myapplication.generator.ServiceGenerator
import com.example.myapplication.models.Article
import com.example.myapplication.models.NewsResponse
import com.example.myapplication.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        val appDatabase = DatabaseGenerator.getInstance(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rvNews = findViewById<RecyclerView>(R.id.recyclerView)
        val serviceGenerator = ServiceGenerator.buildService(APIService::class.java)
        val call =
            serviceGenerator.getLatestNews(ServiceGenerator.QUERY_STRING, ServiceGenerator.API_KEY)


        if (checkForInternet(this)) {
            Toast.makeText(this, "there are internet", Toast.LENGTH_LONG).show()
            //request data
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.body()?.status.equals(ServiceGenerator.STATUS_OK)) {
                        rvNews.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = response.body()?.let { it1 -> CustomAdapter(it1.articles) }

                            Log.d(
                                ServiceGenerator.TAG_SUCCESS,
                                response.body()?.articles.toString()
                            )
                            appDatabase.getArticlesDao().apply {
                                if(getAllArticles().size>0)
                                    deleteAll()
                                response.body()?.articles?.let { insertArticles(it) }
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "can't get data check you internet connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "call failed check you internet connection",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e(ServiceGenerator.TAG_Failure, t.message.toString())

                }

            })

        } else {
            Toast.makeText(this, "there are no internet", Toast.LENGTH_LONG).show()
            rvNews.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = CustomAdapter(appDatabase.getArticlesDao().getAllArticles())
            }
        }

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