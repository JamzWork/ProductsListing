package com.example.productlisting.data.repository

import com.example.productlisting.data.repository.base.BaseRepository
import com.example.productlisting.data.repository.products.ProductsRDS
import com.example.productlisting.data.Result
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.data.repository.products.mapper.transformProducts
import com.example.productlisting.utils.Constants
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsRDS: ProductsRDS,
) : BaseRepository() {

    suspend fun getProducts( ): Result<List<Product>> {

        val response = productsRDS.getProducts()
        return if (response is Result.Success) {
            if (response.data.successful) {
                Result.Success(transformProducts(response.data.products))
            } else {
                Result.Failure(false, null, null, response.data.errorMessage)
            }
        } else {
            Result.Failure(true, null, null, Constants.Error.SOMETHING_WENT_WRONG)
        }
    }

}