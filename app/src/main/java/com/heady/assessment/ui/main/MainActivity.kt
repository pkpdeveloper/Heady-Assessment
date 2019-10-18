package com.heady.assessment.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.heady.assessment.R
import com.heady.assessment.data.SyncManager
import com.heady.assessment.network.response.Category
import com.heady.assessment.network.response.Product
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.presenter.main.MainPresenter
import com.heady.assessment.util.CategoryManager
import com.heady.assessment.view.main.MainView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainView {
    @Inject
    internal lateinit var presenter: MainPresenter
    @Inject
    internal lateinit var syncManager: SyncManager
    private lateinit var toolBar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private var responseData: ResponseData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar = findViewById(R.id.toolBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.design_navigation_view)

        setUpToolBar()
        presenter.setView(this)
        presenter.loadData(syncManager)
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)

        }


        drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.apply {
            isDrawerIndicatorEnabled = true
            syncState()
        }
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        drawerToggle.syncState()
        super.onPostCreate(savedInstanceState, persistentState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        drawerToggle.onConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        responseData?.let { data ->
            if (item.groupId == 0) {
                val productList = mutableListOf<Product>()
                data.categories.forEach {
                    productList.addAll(it.products)
                }
                changeFragment("All Categories", productList)
            }

            if (item.groupId == 1) {
                data.rankings.forEach { ranking ->
                    if (ranking.ranking.length == item.itemId) {
                        val productList = mutableListOf<Product>()
                        ranking.products.forEach { product ->
                            findProduct(product.id)?.also {
                                productList.add(it)
                            }

                        }
                        changeFragment(ranking.ranking, productList)
                    }
                }
            }

            if (item.groupId == 2) {
                data.categories.forEach {
                    if (it.id == item.itemId) {
                        changeFragment(it.name ?: "Unknown category", it.products)
                    }
                }
            }
        }

        return false
    }

    private fun findProduct(productId: Int): Product? {
        responseData?.let { data ->
            data.categories.forEach { category ->
                category.products.forEach {
                    if (it.id == productId) {
                        return it
                    }
                }

            }
        }
        return null
    }

    private fun changeFragment(title: String, products: List<Product>) {
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putParcelableArrayList(
            "product_list",
            ArrayList(products)
        )
        val fragment = MainFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        drawerLayout.closeDrawer(Gravity.LEFT)
    }

    override fun displayData(responseData: ResponseData) {
        this.responseData = responseData

        navigationView.menu.add(0, 1, 0, "Home")

        responseData.rankings.forEach {
            navigationView.menu.add(1, it.ranking.length, 0, it.ranking)
        }
        val sortedCategory = CategoryManager.getSortedByLevel(responseData.categories)
        displayMenuItems(sortedCategory)
        changeFragment("All Products", CategoryManager.getAllProducts(responseData.categories))
    }

    private fun displayMenuItems(sortedCategoryList: List<Category>) {
        sortedCategoryList.forEach {
            val subMenu = navigationView.menu.addSubMenu(2, it.id, 0, it.name)
            recursiveAddMenu(subMenu, it, responseData?.categories)
        }
    }

    private fun recursiveAddMenu(
        subMenu: SubMenu,
        category: Category,
        categoryList: List<Category>?
    ) {
        category.child_categories.map { CategoryManager.getCategoryFromId(it, categoryList) }
            .forEach {
                it?.let {
                    if (it.products.isNotEmpty() && it.child_categories.isEmpty()) {
                        subMenu.add(2, it.id, Menu.NONE, it.name)
                    } else if (it.child_categories.isEmpty()) {
                        subMenu.add(2, it.id, Menu.NONE, it.name)
                    } else {
                        subMenu.addSubMenu(category.id, it.id, Menu.NONE, it.name)
                    }

                    recursiveAddMenu(subMenu, it, categoryList)
                }
            }
    }

    override fun onError() {

    }

}
