package com.example.myapplication.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.squareup.picasso.Picasso

object Utils {

    fun log(message: String) {
        if (BuildConfig.DEBUG)
            Log.d(Constants.TAG, message)
    }

    fun loadImage(imageView: ImageView, url: String?) {
        if (url != null)
            Picasso.get().load(url).placeholder(R.drawable.placeholder).into(imageView)
    }

    fun toast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun toast(message: Int, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}