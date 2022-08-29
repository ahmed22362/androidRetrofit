package com.example.myapplication.models.database

import android.content.Context
import androidx.room.Room
import com.example.myapplication.utils.Constants


class DatabaseGenerator {

    private var instance: AppDatabase? = null


    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = buildRoomDB(context)
            }
        }
        return instance!!
    }


    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
}