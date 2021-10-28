package com.example.productlisting.ui.productDetails.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.productlisting.data.repository.ProductsRepository
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.productlisting.data.Result
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.utils.Constants

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    application: Application,
    private val productsRepository: ProductsRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    private val _productDetails: MutableLiveData<Product> = MutableLiveData()
    val productDetails: LiveData<Product> = _productDetails

    init {
        savedStateHandle.get<Int>(Constants.Bundle.PRODUCT_ID)?.let {
            getProductDetails(it)
        }
    }

    private fun getProductDetails(productId: Int)
    {
        viewModelScope.launch {
            val result = productsRepository.getProductsDetails(productId)
            if (result is Result.Success) {
                _productDetails.value = result.data
            } else if (result is Result.Failure) {
                showToast(Constants.Error.SOMETHING_WENT_WRONG)
            }
        }
    }

}