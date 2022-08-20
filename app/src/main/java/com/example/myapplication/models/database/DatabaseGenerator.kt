package com.example.myapplication.models.database

import android.content.Context
import android.util.Log
import androidx.room.Room


class DatabaseGenerator {

    companion object {
         var INSTANCE: AppDatabase? = null
    }
    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            Log.e("room","room created")
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }


    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Articles.db"
        ).allowMainThreadQueries().build()
}