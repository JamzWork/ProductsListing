package com.example.productlisting.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import kotlinx.android.synthetic.main.loading_view_detail.shimmerFrameLayoutDetail

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<ProductDetailBinding>() {

    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProductDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiEvents(productDetailsViewModel)
        setupHeader()
        subscribeToObservables()
        showLoading()
    }

    private fun setupHeader(){
        binding.viewItemHeader.ivBack.show()
        binding.viewItemHeader.ivBack.setOnClickListener{
            back()
        }

        binding.viewItemHeader.tvHeader.text = resources.getText(R.string.h_product_Details)
    }

    private fun setupViews(mProduct: Product){
        binding.viewProductDetail.tvtitle.text = "${mProduct.title}"
        binding.viewProductDetail.tvdescription.text = "${mProduct.description}"
        binding.viewProductDetail.tvRating.text = "${mProduct.rating.rate} (${mProduct.rating.count})"
        binding.viewProductDetail.tvPriceTag.text = "Rs ${mProduct.price}"

        Glide.with(binding.root.context)
            .load(mProduct.image)
            .into(binding.viewProductDetail.ivProductPicture)
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
        binding.viewProductDetail.productDetailsView.show()
        shimmerFrameLayoutDetail.stopShimmer()
        shimmerFrameLayoutDetail.hide()
    }

    private fun showLoading(){
        binding.viewProductDetail.productDetailsView.hide()
        shimmerFrameLayoutDetail.show()
        shimmerFrameLayoutDetail.startShimmer()
    }

}