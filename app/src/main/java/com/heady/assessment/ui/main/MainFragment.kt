package com.heady.assessment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heady.assessment.R
import com.heady.assessment.network.response.Product

class MainFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
    private lateinit var emptyTextView: TextView
    private val productAdapter = ProductAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = view.findViewById(R.id.recycleView)
        emptyTextView = view.findViewById(R.id.emptyTextView)
        recycleView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = productAdapter
        }
        val bundle = this.arguments
        val title = bundle?.getString("title")
        activity?.title = title
        val productList = bundle?.getParcelableArrayList<Product>("product_list")
        if (productList.isNullOrEmpty()) {
            emptyTextView.visibility = View.VISIBLE
        } else {
            productList.let { productAdapter.setData(it) }

        }
    }
}