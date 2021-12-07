package com.example.secondgallery.presentation.newPhotos

import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentNewBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewFragment : BaseFragment<NewView, NewPresenter, FragmentNewBinding>(PhotoType.New.raw),
    NewView {

    @ProvidePresenter
    fun providePresenter(): NewPresenter = App.appComponent.provideNewPresenter()

    @InjectPresenter
    override lateinit var presenter: NewPresenter

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

        swipeRefreshLayout.setColorScheme( // todo
            R.color.white,
            R.color.black
        )

        progressBar = requireView().findViewById(R.id.progressbar)
    }

    override fun getSearchablePhotos(name: String) {
        presenter.getSearchPhotos(name)
    }

}