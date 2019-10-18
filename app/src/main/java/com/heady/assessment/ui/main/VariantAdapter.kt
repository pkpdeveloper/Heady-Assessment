package com.heady.assessment.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heady.assessment.R
import com.heady.assessment.network.response.Product
import com.heady.assessment.network.response.Variant

class VariantAdapter(private val product: Product, private val variants: List<Variant>) :
    RecyclerView.Adapter<VariantAdapter.VariantHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantHolder {
        return VariantHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_variant_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return variants.size
    }

    override fun onBindViewHolder(holder: VariantHolder, position: Int) {
        val variant = variants[position]
        variant.let {
            val priceStringBuilder = StringBuilder()
            priceStringBuilder.append("Base price :- ${it.price}\n")
            product.tax?.let { tax ->
                val taxCount = it.price * tax.value / 100.0f
                priceStringBuilder.append("${tax.name} :- $taxCount\n")
                priceStringBuilder.append("Total price :- ${it.price + taxCount}")
            }
            holder.tvPrice.text = priceStringBuilder
            holder.tvSize.text = "Size : - ${it.size}"
            val bgColor = getColorDrawable(it.color)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.colorView.background = bgColor
            } else {
                holder.colorView.setBackgroundDrawable(bgColor)
            }
        }
    }

    private fun getColorDrawable(color: String): Drawable? {
        return when (color) {
            "Blue" -> ColorDrawable(Color.BLUE)
            "Red" -> ColorDrawable(Color.RED)
            "White" -> ColorDrawable(Color.WHITE)
            "Black" -> ColorDrawable(Color.BLACK)
            "Yellow" -> ColorDrawable(Color.YELLOW)
            "Grey" -> ColorDrawable(Color.GRAY)
            "Light Blue" -> ColorDrawable(Color.parseColor("#ADD8E6"))
            "Brown" -> ColorDrawable(Color.parseColor("#A52A2A"))
            "Green" -> ColorDrawable(Color.GREEN)
            "Silver" -> ColorDrawable(Color.parseColor("#C0C0C0"))
            "Golden" -> ColorDrawable(Color.parseColor("#D4AF37"))
            else -> throw IllegalArgumentException("Color name not mapped for $color")
        }
    }

    inner class VariantHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val colorView = itemView.findViewById<View>(R.id.colorView)
        internal val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        internal val tvSize = itemView.findViewById<TextView>(R.id.tvSize)

    }
}