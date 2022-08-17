package com.example.myapplication.generator

import android.content.Context
import androidx.room.Room
import com.example.myapplication.database.ArticlesDatabase

object DatabaseGenerator {
    private var INSTANCE: ArticlesDatabase? = null
    fun getInstance(context: Context): ArticlesDatabase {
        if (INSTANCE == null) {
            synchronized(ArticlesDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context,
            ArticlesDatabase::class.java,
            "Articles.db"
        ).allowMainThreadQueries().build()
}