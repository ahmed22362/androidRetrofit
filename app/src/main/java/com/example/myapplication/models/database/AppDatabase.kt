package com.example.myapplication.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.pojo.Article
import com.example.myapplication.utils.Constants

@Database(
    entities = [Article::class],
    version = Constants.DATABASE.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}