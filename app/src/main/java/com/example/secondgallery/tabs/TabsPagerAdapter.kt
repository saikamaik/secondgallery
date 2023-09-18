package com.example.secondgallery.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.secondgallery.presentation.newPhotos.NewPhotoFragment
import com.example.secondgallery.presentation.popularPhotos.PopularPhotoFragment

class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewPhotoFragment()
            1 -> PopularPhotoFragment()
            else -> NewPhotoFragment()
        }
    }

}
