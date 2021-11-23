package com.example.secondgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondgallery.presentation.homePage.HomeFragment
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
//    private val addPhotoFragment = AddPhotoFragment()
//    private val profileFragment = ProfileFragment()
    private val welcomeFragment = WelcomeFragment()

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> openFragment(homeFragment)
//                R.id.navigation_upload -> openFragment(addPhotoFragment)
//                R.id.navigation_profile -> openFragment(profileFragment)
            }
            true
        }

    private fun openFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(homeFragment)
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}