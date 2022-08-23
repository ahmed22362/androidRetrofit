package com.example.myapplication.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.pojo.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}