package com.example.secondgallery.presentation.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.secondgallery.R
import com.example.secondgallery.SearchViewModel
import com.example.secondgallery.databinding.FragmentHomeBinding
import com.example.secondgallery.presentation.newPhotos.NewFragment
import com.example.secondgallery.presentation.popularPhotos.PopularFragment
import com.example.secondgallery.tabs.TabsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


private const val ARG_OBJECT = "object"

class HomeFragment : Fragment() {

    private lateinit var adapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var searchViewModel: SearchViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var newFragment: NewFragment = NewFragment()
        var popularFragment = PopularFragment()

        requireActivity().navigationView.visibility = View.VISIBLE

        val tabTitle = arrayOf("New", "Popular")

        search_bar.queryHint = "Search"

        adapter = TabsPagerAdapter(this)
        tabs_viewpager.adapter = adapter
        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
//        searchViewModel.getQuery()?.observe(viewLifecycleOwner, Observer<String>() {
//            if (it != null) {
//                searchViewModel.setQuery(newText)
//            }
//        })

        val searchView = search_bar as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchViewModel.setQuery(newText)
//                    if (tab_layout.selectedTabPosition == 0 ) {
//                        newFragment.searchViewModel.getQuery()
//                    } else if (tab_layout.selectedTabPosition == 1) {
//                        popularFragment.searchViewModel.getQuery()
//                    }
                }
                return true;
            }

        })


    }

}