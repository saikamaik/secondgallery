package com.example.secondgallery.presentation.popularPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.presentation.basemvp.BaseFragment
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
// TODO Лучше создать enum class для типов new и popular
class PopularFragment : PopularView, BaseFragment<PopularView, PopularPresenter>("popular") {

        @InjectPresenter
        override lateinit var presenter: PopularPresenter

        @ProvidePresenter
        fun providePresenter(): PopularPresenter = App.appComponent.providePopularPresenter()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_popular, container, false)
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
        // TODO Отступы


    }