package com.example.productlisting.data.repository.products.local

class Products(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rate,
)
