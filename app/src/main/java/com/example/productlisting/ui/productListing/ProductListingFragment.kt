package com.example.productlisting.ui.productListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.productlisting.databinding.ProductListingBinding
import com.example.productlisting.ui.base.fragment.BaseFragment
import com.example.productlisting.ui.productListing.viewModel.ProductListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : BaseFragment<ProductListingBinding>() {
    private val registerViewModel: ProductListingViewModel by viewModels()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProductListingBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = registerViewModel
        binding.fragment = this
    }

}