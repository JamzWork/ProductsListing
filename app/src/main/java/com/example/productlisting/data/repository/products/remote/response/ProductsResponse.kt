package com.example.productlisting.data.repository.products.remote.response

import com.example.productlisting.data.repository.base.BaseResponse
import com.google.gson.annotations.SerializedName

class ProductsResponse constructor(
    @SerializedName(value = "Value")
    val products: List<ProductsDTO>
) : BaseResponse() {
}