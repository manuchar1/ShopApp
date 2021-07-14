package com.tbcacademy.shopapp.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Post(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("owner")
    val owner: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("price_type")
    val priceType: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("urls")
    val urls: List<String>
)