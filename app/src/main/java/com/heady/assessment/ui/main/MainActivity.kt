package com.heady.assessment.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.heady.assessment.R
import com.heady.assessment.data.DataManager
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.presenter.main.MainPresenter
import com.heady.assessment.presenter.main.MainPresenterImpl
import com.heady.assessment.view.main.MainView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainView {
    private val presenter: MainPresenter = MainPresenterImpl()
    private lateinit var toolBar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar = findViewById(R.id.toolBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.design_navigation_view)

        setUpToolBar()
        presenter.setView(this)
        presenter.loadData(DataManager.getDataBase())

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment())
            .commit()
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
        return false
    }

    override fun displayData(responseData: ResponseData) {
        responseData.rankings.forEach {
            val menuItem = navigationView.menu.add(0, 0, 0, it.ranking)
        }
        responseData.categories.forEach {
            val menuItem = navigationView.menu.add(1, it.id, 0, it.name)
        }

    }

    override fun onError() {

    }

}
