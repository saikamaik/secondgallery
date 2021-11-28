package com.example.secondgallery.presentation.basemvp

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.domain.entity.PhotoModel
import com.example.secondgallery.R
import com.example.secondgallery.adapter.PaginationScrollListener
import com.example.secondgallery.adapter.RecyclerAdapter
import com.example.secondgallery.presentation.imageDetail.ImageDetailFragment
import moxy.MvpAppCompatFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseFragment<V : BaseView, P: BasePresenter<V>>(var type: String): BaseView,
    MvpAppCompatFragment() {

    protected abstract val presenter: P
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var placeholder: View
    private lateinit var adapter: RecyclerAdapter

    override fun initRecyclerView(photos: ArrayList<PhotoModel>) {
        adapter = RecyclerAdapter(photos, object : RecyclerAdapter.MyViewHolder.Callback {
            override fun onImageClicked(item: PhotoModel) {
                presenter.onImageClicked(item)
            }
        })
        var linearLayoutManager = GridLayoutManager(this.context, 2)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, 4)
        }
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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

    override fun changePlaceholderVisibility(IsNetworkAvailable: Boolean) {
        if (IsNetworkAvailable) {
            recyclerView.visibility = View.VISIBLE
            placeholder.visibility = View.GONE
        } else {
            adapter.notifyDataSetChanged()
            recyclerView.visibility = View.INVISIBLE
            placeholder.visibility = View.VISIBLE
        }
    }


    @Throws(ParseException::class)
    open fun convertToDateAndTime(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())
        val d = sdf.parse(date)
        return output.format(d)
    }

    override fun navigateToImageDetailFragment(photoModel: PhotoModel) {
        val args = Bundle()
        args.putString("imageName", photoModel.name)
        args.putString("imageDateCreate", convertToDateAndTime(photoModel.dateCreate))
        args.putString("imageDescription", photoModel.description)
        args.putString("imageLink", photoModel.image.name)
        ImageDetailFragment().arguments = args
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fl_container, ImageDetailFragment())
            .addToBackStack(null)
            .commit()
    }
}