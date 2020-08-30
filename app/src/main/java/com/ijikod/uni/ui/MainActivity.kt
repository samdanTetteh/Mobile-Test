package com.ijikod.uni.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ijikod.uni.R
import com.ijikod.uni.ui.Factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_holder) as NavHostFragment
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navHostFragment.navController.graph)
    }


    private var menuLayout: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        supportFragmentManager.fragmentFactory = FragmentFactory()

        // Nav controller navigation listener
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contentFragment -> {
                    menuLayout = R.menu.save_menu
                    invalidateOptionsMenu()
                }

                else -> {
                    menuLayout = null
                    invalidateOptionsMenu()
                }
            }


            // setup toolbar with navigation component
            toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuLayout?.let { menuInflater.inflate(it, menu) }
        return true

    }
}
