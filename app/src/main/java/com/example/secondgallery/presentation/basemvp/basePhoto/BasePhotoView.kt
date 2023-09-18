package com.example.secondgallery.presentation.basemvp.basePhoto

import com.example.domain.entity.photo.PhotoModel
import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BasePhotoView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getSearchPhotos(name: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addExtraItems(position: Int, quantity: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addNewItems()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addNewSearchItems()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(photos: ArrayList<PhotoModel>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initViews()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeSwipeRefreshState(isRefreshing: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeProgressViewState(isLoading: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changePlaceholderVisibility(isNetworkAvailable: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun navigateToImageDetailFragment(photoModel: PhotoModel, username: String?)
}