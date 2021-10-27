package com.example.productlisting.ui.productListing.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.productlisting.data.repository.ProductsRepository
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.productlisting.data.Result

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    application: Application,
    private val productsRepository: ProductsRepository
) : BaseViewModel(application) {

    init {
        getAllProducts()
    }

    private fun getAllProducts()
    {
        viewModelScope.launch {
            val result = productsRepository.getProducts()
            if (result is Result.Success) {

            } else if (result is Result.Failure) {

            }
        }
    }

}