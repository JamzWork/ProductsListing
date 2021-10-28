package com.example.productlisting.ui.productListing.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlisting.data.repository.products.local.Product
import com.example.productlisting.databinding.ItemProductBinding
import com.example.productlisting.ui.productListing.clickListeners.ProductClickListener

class ProductsAdapter(private val mProductClickListener: ProductClickListener) :
    ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(
        ProductItemDiffCallback()
    ) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ProductsViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    inner class ProductsViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mProduct: Product) {
            binding.tvTitle.text = mProduct.title
            binding.tvTitle.ellipsize = TextUtils.TruncateAt.END
            binding.tvDescription.text = mProduct.description
            binding.tvRating.text = "${mProduct.rating.rate} (${mProduct.rating.count})"
            binding.tvPriceTag.text = "Rs ${mProduct.price}"

            Glide.with(binding.root.context)
                .load(mProduct.image)
                .into(binding.ivProductPicture)

            binding.clView.setOnClickListener{
                mProductClickListener.onClick(mProduct)
            }
        }
    }


    class ProductItemDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

}
