package com.example.productlisting.data.repository.products.remote.response.products

import com.example.productlisting.data.repository.base.BaseResponse
import com.example.productlisting.data.repository.products.remote.response.ProductDTO

class ProductsResponse constructor(
    val products: List<ProductDTO>
) : BaseResponse() {

}


