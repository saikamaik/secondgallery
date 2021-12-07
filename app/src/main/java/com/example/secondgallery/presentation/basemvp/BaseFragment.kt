package com.example.secondgallery.presentation.basemvp

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.example.domain.entity.PhotoModel
import com.example.secondgallery.R
import com.example.secondgallery.SearchViewModel
import com.example.secondgallery.adapter.PaginationScrollListener
import com.example.secondgallery.adapter.RecyclerAdapter
import com.example.secondgallery.presentation.imageDetail.ImageDetailFragment
import com.example.secondgallery.utils.DateUtils
import moxy.MvpAppCompatFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// todo Константы лучше хранить либо в отдельных файлах, либо в компаньонах
const val IMAGE_NAME = "imageName"
const val IMAGE_DATE_CREATION = "imageDateCreate"
const val IMAGE_DESCRIPTION = "imageDescription"
const val IMAGE_LINK = "imageLink"
const val IMAGE_USERNAME = "imageUsername"

// todo 1. Переименовать этот класс на что-то типа BasePhotoFragment
// todo 2. Попробовать вынести базовую логику из этого фрагмента в BaseFragment
abstract class BaseFragment<
        V : BaseView,
        P : BasePresenter<V>,
        VB : ViewBinding
>(var type: String) : BaseView, MvpAppCompatFragment() {

    protected abstract val presenter: P
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var placeholder: View
    private lateinit var adapter: RecyclerAdapter

    var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initializeBinding()
        return binding.root
    }

    abstract fun initializeBinding(): VB

    override fun initRecyclerView(photos: ArrayList<PhotoModel>) {
        adapter = RecyclerAdapter(photos, object : RecyclerAdapter.Callback {
            override fun onImageClicked(item: PhotoModel) {
                presenter.onImageClicked(item)
            }
        })

        // todo Можно вынести в метод onConfigurationChanged
        var linearLayoutManager = GridLayoutManager(this.context, 2)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, 4)
        }
        recyclerView.layoutManager = linearLayoutManager
        //
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView.setHasFixedSize(true)

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                presenter.getPhotos()
            }
        })
    }

    override fun addExtraItems(position: Int, quantity: Int) {
        adapter.notifyItemRangeInserted(position, quantity)
    }

    override fun addNewItems() {
        adapter.notifyDataSetChanged()
    }

    override fun changeSwipeRefreshState(isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    override fun changeProgressViewState(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun changePlaceholderVisibility(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            recyclerView.visibility = View.VISIBLE
            placeholder.visibility = View.GONE
        } else {
            adapter.notifyDataSetChanged()
            recyclerView.visibility = View.INVISIBLE
            placeholder.visibility = View.VISIBLE
        }
    }

    override fun navigateToImageDetailFragment(photoModel: PhotoModel) {
        val args = Bundle()
        args.putString(IMAGE_NAME, photoModel.name)
        args.putString(IMAGE_DATE_CREATION, DateUtils.checkDateFormat(photoModel.dateCreate))
        args.putString(IMAGE_DESCRIPTION, photoModel.description)
        args.putString(IMAGE_LINK, photoModel.image.name)
        args.putString(IMAGE_USERNAME, photoModel.user)
        ImageDetailFragment().arguments = args
        findNavController().navigate(R.id.imageDetailFragment, args)
    }

}