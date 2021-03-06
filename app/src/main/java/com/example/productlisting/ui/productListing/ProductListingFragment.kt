package com.example.productlisting.ui.productListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.databinding.ProductListingBinding
import com.example.productlisting.ui.base.fragment.BaseFragment
import com.example.productlisting.ui.productListing.adapter.ProductsAdapter
import com.example.productlisting.ui.productListing.clickListeners.ProductClickListener
import com.example.productlisting.ui.productListing.viewModel.ProductListingViewModel
import com.example.productlisting.utils.extensions.hide
import com.example.productlisting.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : BaseFragment<ProductListingBinding>() {

    private val productViewModel: ProductListingViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProductListingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiEvents(productViewModel)
        setupViews()
    }

    private fun setupViews(){
        setupRecyclerView()
        subscribeToObservables()
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            showLoading()
            productViewModel.getAllProducts()
        }
    }

    private fun subscribeToObservables(){
        productViewModel.productsListing.observe(viewLifecycleOwner, {
            it?.let {
                productsAdapter.submitList(it)
                showList()
            }
        })
    }

    private fun showList(){
        binding.rvList.show()
        binding.viewLoadingList.shimmerFrameLayout.stopShimmer()
        binding.viewLoadingList.shimmerFrameLayout.hide()
    }

    private fun showLoading(){
        binding.rvList.hide()
        binding.viewLoadingList.shimmerFrameLayout.show()
        binding.viewLoadingList.shimmerFrameLayout.startShimmer()
    }

    private fun setupRecyclerView() {
        binding.rvList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvList.itemAnimator = null
        productsAdapter = ProductsAdapter(object : ProductClickListener {
            override fun onClick(product: Product) {
                 navigateByDirections(ProductListingFragmentDirections.actionProductListingFragmentToProductDetailsFragment(product.id))
            }
        })
        binding.rvList.adapter = productsAdapter
    }

    override fun onPause() {
        super.onPause()
        binding.viewLoadingList.shimmerFrameLayout.stopShimmer()
        binding.viewLoadingList.shimmerFrameLayout.hide()
    }

}