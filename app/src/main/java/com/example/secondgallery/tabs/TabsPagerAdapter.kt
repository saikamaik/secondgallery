package com.example.secondgallery.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
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
