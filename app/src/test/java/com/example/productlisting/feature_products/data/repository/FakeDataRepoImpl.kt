package com.example.productlisting.feature_products.data.repository

import com.example.productlisting.data.Result
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.data.repository.products.local.Rate
import javax.inject.Inject

class FakeDataRepoImpl @Inject constructor(
) : ProductsRepository {

    var mData = mutableListOf<Product>()

    override suspend fun getProducts():  List<Product> {
        mData =  mutableListOf(
            Product(
                id = 1,
                title = "fake title",
                price = 34.34,
                description = "fake description",
                category = "test",
                image = "fake",
                rating = Rate(
                    4.5,
                    3
                )
            ),
            Product(
                id = 1,
                title = "fake title",
                price = 34.34,
                description = "fake description",
                category = "test",
                image = "fake",
                rating = Rate(
                    4.5,
                    3
                )
            )
        )

    //    return Result.Success(data)
        return mData
    }

}


interface ProductsRepository {
    suspend fun getProducts(): List<Product>
}