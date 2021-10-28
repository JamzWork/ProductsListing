package com.example.productlisting.data.repository.products

import com.example.productlisting.data.Result
import com.example.productlisting.data.repository.base.BaseRDS
import com.example.productlisting.data.repository.products.remote.response.ProductDetailResponse
import com.example.productlisting.data.repository.products.remote.response.ProductsResponse
import com.example.productlisting.data.repository.products.remote.service.ProductsService
import javax.inject.Inject

class ProductsRDS @Inject constructor(private val productService: ProductsService) : BaseRDS() {
    suspend fun getProducts(): Result<ProductsResponse> {
        return safeApiCall {
            val result = ProductsResponse(productService.getProducts())
            result.successful = true
            return@safeApiCall result
        }
    }

    suspend fun getProductDetails(
        productId: Int
    ): Result<ProductDetailResponse> {
        return safeApiCall {
            val result = ProductDetailResponse(productService.getProductsDetails(productId))
            result.successful = true
            return@safeApiCall result
        }
    }
}