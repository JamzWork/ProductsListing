package com.example.productlisting.feature_products

import com.example.productlisting.feature_products.data.repository.FakeDataRepoImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject
import  com.google.common.truth.Truth.assertThat

@RunWith(JUnit4::class)
class ProductListingViewModelTest {

    @Inject
    private lateinit var getProducts: FakeDataRepoImpl

    @Before
    fun setup(){
        getProducts = FakeDataRepoImpl()
    }

    @Test
    fun `has_values`() = runBlocking {
         getProducts.getProducts()
        val mD = getProducts.mData
        assert(mD[0].title == "fake title")
        assertThat(mD[0].id).isEqualTo(1)

    }

}