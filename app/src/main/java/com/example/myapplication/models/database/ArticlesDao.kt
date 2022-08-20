package com.example.myapplication.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.models.POJO.Article

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(articles:List<Article>)

    @Query("SELECT * FROM Articles")
    fun getAllArticles(): List<Article>

    @Query("DELETE FROM Articles")
    fun deleteAll():Int
}