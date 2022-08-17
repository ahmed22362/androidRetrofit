package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.Article


@Database(
    entities = [Article::class],
    version = 1
)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun getArticlesDao():ArticlesDao

}