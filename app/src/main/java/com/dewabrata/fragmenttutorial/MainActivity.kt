package com.dewabrata.fragmenttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.Toolbar

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.dewabrata.fragmenttutorial.adapter.ViewPagerAdapter
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var  appBarConfiguration: AppBarConfiguration
    lateinit var  navController: NavController

    lateinit var viewPager : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewPager = findViewById(R.id.viewPager)
//
//        val adapter = ViewPagerAdapter(this)
//        viewPager.adapter = adapter

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        val navView : NavigationView =  findViewById<NavigationView>(R.id.nav_view)
        navView.setupWithNavController(navController)

//        navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.nav_fragment1 -> {
//                    navController.navigate(R.id.fragmentPage1)
//                    true
//                }
//
//                R.id.nav_fragment2 -> {
//                    navController.navigate(R.id.fragmentPage2)
//                    true
//                }
//
//                R.id.nav_fragment3 -> {
//                    navController.navigate(R.id.fragmentPage3)
//                    true
//                }
//
//                else -> false
//            }
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}