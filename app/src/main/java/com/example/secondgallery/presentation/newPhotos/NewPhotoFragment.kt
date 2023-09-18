package com.example.secondgallery.presentation.newPhotos

import androidx.core.content.res.ResourcesCompat
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentNewBinding
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoFragment
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewPhotoFragment : BasePhotoFragment<NewPhotoView,
        NewPhotoPresenter,
        FragmentNewBinding>(PhotoType.New.raw, 1),
    NewPhotoView {

    @InjectPresenter
    override lateinit var presenter: NewPhotoPresenter

    @ProvidePresenter
    fun providePresenter(): NewPhotoPresenter = App.appComponent.provideNewPresenter()

    override var spanCount: Int = 2
    override var spanCountLand: Int = 4

    override fun initializeBinding(): FragmentNewBinding {
        return FragmentNewBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        recyclerView = requireView().findViewById(R.id.recyclerViewNew)
        swipeRefreshLayout = requireView().findViewById(R.id.swiperefresh)
        placeholder = requireView().findViewById(R.id.newPlaceholder)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.onSwipeRefresh()
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