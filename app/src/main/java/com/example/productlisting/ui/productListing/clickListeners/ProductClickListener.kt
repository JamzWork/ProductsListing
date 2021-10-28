package com.example.productlisting.ui.productListing.clickListeners

import com.example.productlisting.data.repository.products.local.Product

interface ProductClickListener {
    fun onClick(product: Product)
}