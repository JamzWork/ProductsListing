package com.example.productlisting.ui.productListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlisting.databinding.ProductListingBinding
import com.example.productlisting.ui.base.fragment.BaseFragment
import com.example.productlisting.ui.productListing.adapter.ProductsAdapter
import com.example.productlisting.ui.productListing.viewModel.ProductListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListingFragment : BaseFragment<ProductListingBinding>() {

    private val productViewModel: ProductListingViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProductListingBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = productViewModel
        binding.fragment = this
        subscribeUiEvents(productViewModel)
        setupRecyclerView()
        subscribeToObservables()
    }

    private fun subscribeToObservables(){
        productViewModel.productsListing.observe(viewLifecycleOwner, {
            it?.apply {
                productsAdapter.submitList(it)
            }
        })
    }

    private fun setupRecyclerView() {
        binding?.rvList?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding?.rvList?.itemAnimator = null
        productsAdapter = ProductsAdapter()
        binding?.rvList?.adapter = productsAdapter
    }

}