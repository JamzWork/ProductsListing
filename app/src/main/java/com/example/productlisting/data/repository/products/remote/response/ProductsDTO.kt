package com.example.productlisting.data.repository.products.remote.response

import com.google.gson.annotations.SerializedName

class ProductsDTO(
    @SerializedName(value = "Id")
    val id: Int,

    @SerializedName(value = "title")
    val title: String,

    @SerializedName(value = "price")
    val price: Double,

    @SerializedName(value = "description")
    val description: String,

    @SerializedName(value = "category")
    val category: String,

    @SerializedName(value = "image")
    val image: String,

    @SerializedName(value = "rating")
    val rating: RateDTO,
)
