package com.example.myapplication

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

object Utils {

    fun log(message: String) {
        if (BuildConfig.DEBUG)
            Log.d(Constants.TAG, message)
    }

    fun loadImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }

    fun toast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}