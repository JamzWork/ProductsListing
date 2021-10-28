package com.example.productlisting.data.repository.products.remote.service

import com.example.productlisting.data.repository.products.remote.response.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): List<ProductDTO>

    @GET("products/{id}")
    suspend fun getProductsDetails(@Path("id") id: Int): ProductDTO
}