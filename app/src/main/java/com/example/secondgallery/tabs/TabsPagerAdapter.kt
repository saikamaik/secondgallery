package com.example.secondgallery.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.secondgallery.presentation.newPhotos.NewFragment
import com.example.secondgallery.presentation.popularPhotos.PopularFragment

class TabsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewFragment()
            }
            1 -> PopularFragment()
            else -> {
                return NewFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "New"
            else -> {
                return "Popular"
            }
        }
    }
}