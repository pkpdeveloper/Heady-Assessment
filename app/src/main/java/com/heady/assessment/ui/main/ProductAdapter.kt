package com.heady.assessment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heady.assessment.R
import com.heady.assessment.network.response.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    private var items: List<Product>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_product_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = items?.get(position)
        product?.let {
            holder.tvTitle.text = it.name
            holder.variantRecycleView.apply {
                it.variants?.let {
                    layoutManager = GridLayoutManager(this.context, 3)
                    adapter = VariantAdapter(it)
                }
            }

        }
    }

    fun setData(productList: List<Product>) {
        this.items = productList
        notifyDataSetChanged()
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        internal val variantRecycleView =
            itemView.findViewById<RecyclerView>(R.id.variantRecycleView)
    }
}