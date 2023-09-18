package com.example.secondgallery.presentation.popularPhotos

import androidx.core.content.res.ResourcesCompat
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentPopularBinding
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoFragment
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class PopularPhotoFragment : PopularPhotoView,
    BasePhotoFragment<PopularPhotoView, PopularPhotoPresenter, FragmentPopularBinding>(
        PhotoType.Popular.raw,
        1
    ) {

    @InjectPresenter
    override lateinit var presenter: PopularPhotoPresenter

    @ProvidePresenter
    fun providePresenter(): PopularPhotoPresenter = App.appComponent.providePopularPresenter()

    override var spanCount: Int = 2
    override var spanCountLand: Int = 4

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

        swipeRefreshLayout.setColorSchemeColors(
            ResourcesCompat.getColor(resources, R.color.black, null),
            ResourcesCompat.getColor(resources, R.color.pink, null)
        )

        progressBar = requireView().findViewById(R.id.progressbar)
    }

    override fun setUpListeners() {
    }

}