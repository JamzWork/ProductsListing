package com.example.productlisting.data.repository.products.remote.response

import com.google.gson.annotations.SerializedName

class RateDTO(

    @SerializedName(value = "rate")
    val rate: Double,

    @SerializedName(value = "count")
    val count: Int,

)
