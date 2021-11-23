package com.example.secondgallery.tabs

import android.os.Bundle
import android.util.ArrayMap
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.presentation.basemvp.BasePresenter
import com.example.secondgallery.presentation.basemvp.BaseView
import com.example.secondgallery.presentation.homePage.HomeFragment
import com.example.secondgallery.presentation.newPhotos.NewFragment
import com.example.secondgallery.presentation.popularPhotos.PopularFragment

class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewFragment()
            }
            1 -> {
                PopularFragment()
            }
            else -> NewFragment()
        }
    }
}
