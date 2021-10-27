package com.example.productlisting.data.repository.products.remote.response

import com.example.productlisting.data.repository.base.BaseResponse

class ProductsResponse constructor(
    val products: List<ProductDTO>
) : BaseResponse() {

}


