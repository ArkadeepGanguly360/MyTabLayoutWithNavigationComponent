package com.development.mytablayoutwithnavigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.drawer_layout.view.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navigationDestListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->

            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            setDrawerLockMethod(false)

            when (destination.id) {

               /* R.id.today -> {
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }*/
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavController()

        main_content.toolbar_layout.img_humburger_toolbar.onClick()
        drawer_view.home.onClick()
        drawer_view.settings.onClick()
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
        navController = findNavController(R.id.mainNavigationFragment)

        // set navigation graph
        val navGraph = navController.navInflater.inflate(R.navigation.main_navigation)

        navGraph.startDestination = R.id.viewPagerTab

        navController.graph = navGraph
        main_content.bottom_navigation.setupWithNavController(navController)       // connect nav controller with bottom navigation view
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.viewPagerTab,
                R.id.homeFragment,
                R.id.settingsFragment,
                R.id.insights,
                R.id.products,
                R.id.progress,
                R.id.tabOneDetailsFragment,
                R.id.tabTwoDetailsFragment,
                R.id.tabThreeDetailsFragment
            )
        )
        setSupportActionBar(toolbar_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )    // connect nav controller with Toolbar

        nav_view.setupWithNavController(navController)                   //navigation drawer attached with navController
        navController.addOnDestinationChangedListener(navigationDestListener)   //on nav controller destination change listener
    }

    public fun setDrawerOpenStatus(toOpen: Boolean) {
        if (toOpen)
            drawer_layout.openDrawer(GravityCompat.START)
        else
            drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun setDrawerLockMethod(value: Boolean) {
        if (value)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        else
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun View.onClick() {
        this.setOnClickListener {
            when (it.id) {
                main_content.toolbar_layout.img_humburger_toolbar.id -> {
                    setDrawerOpenStatus(true)
                }
                drawer_view.home.id -> {
                    navController.navigate(R.id.homeFragment)
                }
                drawer_view.settings.id -> {
                    navController.navigate(R.id.settingsFragment)
                }
            }
        }
    }
}