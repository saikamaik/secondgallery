package com.example.secondgallery.presentation.homePage

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.secondgallery.App
import com.example.secondgallery.databinding.FragmentHomeBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoFragment
import com.example.secondgallery.tabs.TabsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeView, HomePresenter, FragmentHomeBinding>(), HomeView {

    @Inject
    override lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun providePresenter(): HomePresenter = App.appComponent.provideHomePresenter()

    private lateinit var adapter: TabsPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().navigationView.visibility = View.VISIBLE

        val tabTitle = arrayOf("New", "Popular")

        binding.searchBar.queryHint = "Search"

        adapter = TabsPagerAdapter(this)
        binding.tabsViewpager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.tabsViewpager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        val searchView = binding.searchBar as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val myFragment =
                        childFragmentManager.findFragmentByTag("f" + binding.tabLayout.selectedTabPosition)
                    (myFragment as BasePhotoFragment<*, *, *>).getSearchPhotos(newText)
                }
                return true
            }
        })
    }

    override fun initializeBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setUpListeners() {

    }

}