package com.example.productlisting.data.repository.products.mapper

import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.data.repository.products.local.Rate
import com.example.productlisting.data.repository.products.remote.response.ProductDTO
import com.example.productlisting.data.repository.products.remote.response.RateDTO

fun transformProducts(list: List<ProductDTO>): List<Product> = list.run {
    this.map {
        Product(
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

fun transformProduct(productDTO: ProductDTO): Product = productDTO.run  {
    Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = transformRate(this.rating)
    )
}

