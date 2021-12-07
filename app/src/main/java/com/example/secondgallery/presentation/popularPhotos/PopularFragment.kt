package com.example.secondgallery.presentation.popularPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentPopularBinding
import com.example.secondgallery.databinding.FragmentSignupBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class PopularFragment : PopularView,
    BaseFragment<PopularView, PopularPresenter, FragmentPopularBinding>(PhotoType.Popular.raw) {

    @InjectPresenter
    override lateinit var presenter: PopularPresenter

    @ProvidePresenter
    fun providePresenter(): PopularPresenter = App.appComponent.providePopularPresenter()

    override fun initializeBinding(): FragmentPopularBinding {
        return FragmentPopularBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        recyclerView = requireView().findViewById(R.id.recyclerViewPopular)
        swipeRefreshLayout = requireView().findViewById(R.id.swiperefresh)
        placeholder = requireView().findViewById(R.id.popularPlaceholder)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.onSwipeRefresh()
            swipeRefreshLayout.isRefreshing = false
        }

        swipeRefreshLayout.setColorScheme(
            R.color.white,
            R.color.black
        )

        progressBar = requireView().findViewById(R.id.progressbar)
    }

    override fun getSearchablePhotos(name: String) {
        presenter.getSearchPhotos(name)
    }

}