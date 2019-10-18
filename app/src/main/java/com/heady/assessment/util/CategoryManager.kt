package com.heady.assessment.util

import com.heady.assessment.network.response.Category
import com.heady.assessment.network.response.Product

object CategoryManager {
    fun getSortedByLevel(categories: List<Category>): List<Category> {
        val categoryHashMap = categories.toHashSet()
        categories.forEach {
            if (isSubCategory(it, categories)) {
                categoryHashMap.remove(it)
            }
        }

        return categoryHashMap.toList()
    }

    private fun isSubCategory(category: Category, categories: List<Category>): Boolean {
        categories.forEach {
            it.child_categories.forEach { childCategoryId ->
                if (childCategoryId == category.id.toString())
                    return true
            }
        }
        return false
    }

    fun getAllProducts(categories: List<Category>): List<Product> {
        val productList = mutableListOf<Product>()
        getSortedByLevel(categories).forEach {
            addRecursiveProductsFromCategory(it, productList, categories)
        }
        return productList
    }

    private fun addRecursiveProductsFromCategory(
        category: Category,
        productList: MutableList<Product>,
        categories: List<Category>
    ) {
        productList.addAll(category.products)
        category.child_categories.map { getCategoryFromId(it, categories) }.forEach {
            it?.let { it1 -> addRecursiveProductsFromCategory(it1, productList, categories) }
        }
    }

    fun getCategoryFromId(categoryId: String, categoryList: List<Category>?): Category? {
        categoryList?.forEach {
            if (it.id.toString() == categoryId) {
                return it
            }
        }
        return null

    }
}