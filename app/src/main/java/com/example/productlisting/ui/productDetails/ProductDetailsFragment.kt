package com.example.productlisting.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.productlisting.databinding.ProductDetailBinding
import com.example.productlisting.ui.base.fragment.BaseFragment
import com.example.productlisting.ui.productDetails.viewModel.ProductDetailsViewModel
import com.example.productlisting.utils.extensions.hide
import com.example.productlisting.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.loading_view.*

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<ProductDetailBinding>() {

    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProductDetailBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = productDetailsViewModel
        binding.fragment = this
        subscribeUiEvents(productDetailsViewModel)
        setupViews()
    }

    private fun setupViews(){
        subscribeToObservables()
    }

    private fun subscribeToObservables(){
        productDetailsViewModel.productDetails.observe(viewLifecycleOwner, {
            it?.apply {

            }
        })
    }

    private fun showList(){
        binding.rvList.show()
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.hide()
    }

    private fun showLoading(){
        binding.rvList.hide()
        shimmerFrameLayout.show()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.hide()
    }

}