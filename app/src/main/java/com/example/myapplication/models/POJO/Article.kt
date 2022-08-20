package com.example.myapplication.models.POJO

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("title")
    var title : String ,
    @SerializedName("author")
    var author : String,
    @SerializedName("urlToImage")
    var urlToImage : String,
    @SerializedName("description")
    var description:String? = null,
    @SerializedName("url")
    var url:String? = null,
    @SerializedName("publishedAt")
    var publishedAt:String? = null,
    @SerializedName("content")
    var content: String? =null
):Parcelable
