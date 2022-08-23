package com.example.myapplication.models.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.DATABASE.ARTICLES_TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("content")
    var content: String? = null
) : Parcelable
