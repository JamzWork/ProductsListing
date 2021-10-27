package com.example.productlisting.data.repository.base

import com.google.gson.annotations.SerializedName

open class BaseResponse() {
    @SerializedName(value = "Successful")
    var successful: Boolean = false

    @SerializedName(value = "ErrorMessage")
    val errorMessage: String = ""
}