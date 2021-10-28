package com.example.productlisting.ui.productListing.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class ProductListingViewModel @Inject constructor(
    application: Application,
    private val productsRepository: ProductsRepository
) : BaseViewModel(application) {

    private val _productsListing: MutableLiveData<List<Product>> = MutableLiveData()
    val productsListing: LiveData<List<Product>> = _productsListing

    init {
        getAllProducts()
    }

    fun getAllProducts()
    {
        viewModelScope.launch {
            val result = productsRepository.getProducts()
            if (result is Result.Success) {
                _productsListing.value = result.data
            } else if (result is Result.Failure) {
                showToast(Constants.Error.SOMETHING_WENT_WRONG)
            }
        }
    }

}