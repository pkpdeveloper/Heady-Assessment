package com.heady.assessment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heady.assessment.R
import com.heady.assessment.network.response.Product

class MainFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
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
        recycleView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = productAdapter
        }
        val bundle = this.arguments
        val title = bundle?.getString("title")
        activity?.title = title
        val productList = bundle?.getParcelableArrayList<Product>("product_list")
        productList?.let { productAdapter.setData(it) }
    }
}