package com.example.secondgallery.presentation.newPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.SearchViewModel
import com.example.secondgallery.databinding.FragmentNewBinding
import com.example.secondgallery.databinding.FragmentPopularBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewFragment : BaseFragment<NewView, NewPresenter, FragmentNewBinding>(PhotoType.New.raw),
    NewView {

    @InjectPresenter
    override lateinit var presenter: NewPresenter

    @ProvidePresenter
    fun providePresenter(): NewPresenter = App.appComponent.provideNewPresenter()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.fragment_new, container, false)
//    }

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

        swipeRefreshLayout.setColorScheme(
            R.color.white,
            R.color.black
        )

        progressBar = requireView().findViewById(R.id.progressbar)
    }

//    override fun getSearchablePhotos(name: String) {
//        presenter.getData(this.searchViewModel.getQuery().toString())
//    }

}