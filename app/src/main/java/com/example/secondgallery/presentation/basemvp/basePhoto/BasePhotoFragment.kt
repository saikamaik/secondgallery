package com.example.secondgallery.presentation.basemvp.basePhoto

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.example.domain.entity.photo.PhotoModel
import com.example.secondgallery.R
import com.example.secondgallery.adapter.PaginationScrollListener
import com.example.secondgallery.adapter.RecyclerAdapter
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.presentation.imageDetail.ImageDetailFragment
import com.example.secondgallery.utils.Const.IMAGE_DATE_CREATION
import com.example.secondgallery.utils.Const.IMAGE_DESCRIPTION
import com.example.secondgallery.utils.Const.IMAGE_LINK
import com.example.secondgallery.utils.Const.IMAGE_NAME
import com.example.secondgallery.utils.Const.IMAGE_USERNAME
import com.example.secondgallery.utils.DateUtils
import java.util.*

abstract class BasePhotoFragment<V : BasePhotoView, P : BasePhotoPresenter<V>, VB : ViewBinding>(
    private var type: String,
    private var viewType: Int
) : BasePhotoView, BaseFragment<V, P, VB>() {

    abstract override val presenter: P
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var placeholder: View
    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: GridLayoutManager
    abstract var spanCount: Int
    abstract var spanCountLand: Int

    open fun setUpLayoutManager(spanCount: Int, spanCountLand: Int) {
        linearLayoutManager = GridLayoutManager(this.context, spanCount)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, spanCountLand)
        }
    }

    override fun initRecyclerView(photos: ArrayList<PhotoModel>) {
        adapter = RecyclerAdapter(photos, object : RecyclerAdapter.Callback {
            override fun onImageClicked(item: PhotoModel) {
                presenter.onImageClicked(item)
            }
        })
        setUpLayoutManager(2, 4)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                presenter.checkSearch()
            }
        })
    }

    override fun addExtraItems(position: Int, quantity: Int) {
        adapter.notifyItemRangeInserted(position, quantity)
    }

    override fun addNewItems() {
        adapter.notifyDataSetChanged()
    }

    override fun addNewSearchItems() {
        adapter.notifyDataSetChanged()
    }

    override fun changeSwipeRefreshState(isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    override fun changeProgressViewState(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }

    override fun changePlaceholderVisibility(isNetworkAvailable: Boolean) {
        recyclerView.isVisible = isNetworkAvailable
        placeholder.isVisible = !isNetworkAvailable
        if (!isNetworkAvailable) adapter.notifyDataSetChanged()
    }

    override fun navigateToImageDetailFragment(photoModel: PhotoModel, username: String?) {
        val args = Bundle()
        args.putInt("viewType", viewType)
        args.putString(IMAGE_NAME, photoModel.name)
        args.putString(IMAGE_DATE_CREATION, DateUtils.checkDateFormat(photoModel.dateCreate))
        args.putString(IMAGE_DESCRIPTION, photoModel.description)
        args.putString(IMAGE_LINK, photoModel.image?.name)
        args.putString(IMAGE_USERNAME, username)
        ImageDetailFragment().arguments = args
        findNavController().navigate(R.id.imageDetailFragment, args)
    }

    override fun getSearchPhotos(name: String) {
        presenter.getSearchPhotos(name)
    }

}