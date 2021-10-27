package com.example.productlisting.data.repository

import com.example.productlisting.data.repository.base.BaseRepository
import com.example.productlisting.data.repository.products.ProductsRDS
import com.example.productlisting.data.repository.products.remote.response.ProductsResponse
import com.example.productlisting.data.Result
import com.example.productlisting.utils.Constants
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsRDS: ProductsRDS,
) : BaseRepository() {

    suspend fun getProducts( ): Result<ProductsResponse> {

        val response = productsRDS.getProducts()
        return if (response is Result.Success) {
            if (response.data.successful) {
                Result.Success(response.data)
            } else {
                Result.Failure(false, null, null, response.data.errorMessage)
            }
        } else {
            Result.Failure(true, null, null, Constants.Error.SOMETHING_WENT_WRONG)
        }
    }

}