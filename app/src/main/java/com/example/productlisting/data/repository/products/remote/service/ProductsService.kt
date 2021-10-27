package com.example.productlisting.data.repository.products.remote.service

import com.example.productlisting.data.repository.products.remote.response.ProductsDTO
import com.example.productlisting.data.repository.products.remote.response.ProductsResponse
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): List<ProductsDTO>
}