package com.example.productlisting.ui.productListing.viewModel

import android.app.Application
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    application: Application,
) : BaseViewModel(application) {

    init { }

}