package com.example.productlisting

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.productlisting.data.repository.ProductsRepository
import com.example.productlisting.ui.productListing.viewModel.ProductListingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import org.junit.Assert

@HiltAndroidTest
class SampleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var pVM : ProductListingViewModel

    @Inject
    lateinit var application : Application

    @Inject
    lateinit var productsRepository : ProductsRepository

    @Before
    fun init(){
        hiltRule.inject()
        pVM = ProductListingViewModel(application,productsRepository)
    }

    @Test
    fun `Basic_test`(){
      //  assert(pVM.productsListing.value != null)

    }

}