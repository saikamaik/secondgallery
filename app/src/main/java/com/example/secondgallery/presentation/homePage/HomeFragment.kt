package com.example.secondgallery.presentation.homePage

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.secondgallery.R
import com.example.secondgallery.tabs.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

private const val ARG_OBJECT = "object"

class HomeFragment: Fragment() {

    lateinit var searchView: SearchView
    private lateinit var adapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tabTitle = arrayOf("New", "Popular")

        searchView = view.findViewById(R.id.search_bar)
        searchView.queryHint = "Search"

        adapter = TabsPagerAdapter(this)
        viewPager = view.findViewById(R.id.tabs_viewpager)
        viewPager.adapter = adapter

        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

    }

}