package com.example.productlisting.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.productlisting.R
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.databinding.ProductDetailBinding
import com.example.productlisting.ui.base.fragment.BaseFragment
import com.example.productlisting.ui.productDetails.viewModel.ProductDetailsViewModel
import com.example.productlisting.utils.extensions.hide
import com.example.productlisting.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_header.*
import kotlinx.android.synthetic.main.item_product_detail.*
import kotlinx.android.synthetic.main.loading_view_detail.shimmerFrameLayoutDetail

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
        setupHeader()
        subscribeToObservables()
        showLoading()
    }

    fun setupHeader(){
        iv_back.show()
        iv_back.setOnClickListener{
            back()
        }
        tv_header.text = resources.getText(R.string.h_product_Details)
    }

    private fun setupViews(mProduct: Product){
        tvtitle.text = "${mProduct.title}"
        tvdescription.text = "${mProduct.description}"
        tv_rating.text = "${mProduct.rating.rate} (${mProduct.rating.count})"
        tv_price_tag.text = "Rs ${mProduct.price}"

        Glide.with(binding.root.context)
            .load(mProduct.image)
            .into(ivProductPicture)
    }

    private fun subscribeToObservables(){
        productDetailsViewModel.productDetails.observe(viewLifecycleOwner, {
            it?.apply {
                setupViews(this)
                showDetails()
            }
        })
    }

    private fun showDetails(){
        productDetailsView.show()
        shimmerFrameLayoutDetail.stopShimmer()
        shimmerFrameLayoutDetail.hide()
    }

    private fun showLoading(){
        productDetailsView.hide()
        shimmerFrameLayoutDetail.show()
        shimmerFrameLayoutDetail.startShimmer()
    }

}