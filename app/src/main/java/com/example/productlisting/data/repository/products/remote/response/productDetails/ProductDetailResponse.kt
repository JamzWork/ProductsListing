package com.example.productlisting.data.repository.products.remote.response.productDetails

import com.example.productlisting.data.repository.base.BaseResponse
import com.example.productlisting.data.repository.products.remote.response.ProductDTO

class ProductDetailResponse constructor(
    val product: ProductDTO
) : BaseResponse() {

}


