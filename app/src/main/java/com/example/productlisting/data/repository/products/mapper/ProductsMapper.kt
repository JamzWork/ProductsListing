package com.example.productlisting.data.repository.products.mapper

import com.example.productlisting.data.repository.products.local.Products
import com.example.productlisting.data.repository.products.local.Rate
import com.example.productlisting.data.repository.products.remote.response.ProductsDTO
import com.example.productlisting.data.repository.products.remote.response.ProductsResponse
import com.example.productlisting.data.repository.products.remote.response.RateDTO

fun transformAllProducts(productsResponse: ProductsResponse): List<Products> =
    productsResponse.products.run {
        transformProducts(this)
    }

fun transformProducts(list: List<ProductsDTO>): List<Products> = list.run {
    this.map {
        Products(
            id = it.id,
            title = it.title,
            price = it.price,
            description = it.description,
            category = it.category,
            image = it.image,
            rating = transformRate(it.rating)
        )
    }
}

fun transformRate(rateDTO: RateDTO): Rate = rateDTO.run  {
    Rate(
        count =  rateDTO.count,
        rate =  rateDTO.rate
    )
}
