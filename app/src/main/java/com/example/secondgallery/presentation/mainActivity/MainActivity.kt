package com.example.secondgallery.presentation.mainActivity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.secondgallery.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setupWithNavController(navController)
//        bottomNavigation.setOnItemSelectedListener {
//            navController.navigate(it.itemId)
//            true
//        }
    }

    override fun onBackPressed() {}
}