package com.example.secondgallery.presentation.basemvp

import android.app.Application
import com.example.domain.entity.PhotoModel
import com.example.domain.gateway.PhotoGateway
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

abstract class BasePresenter<V : BaseView>(
    private val context: Application,
    var new: String?,
    var popular: String?,
    val user: String? = null,
    private val photoGateway: PhotoGateway
) : MvpPresenter<V>() {

    var isLoading = false
    private var photos: ArrayList<PhotoModel> = arrayListOf()
    private var page: Int = 1
    private var isLastPage = false
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.initViews()
        viewState.initRecyclerView(photos)
        getFirstPhotos(false)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun onSwipeRefresh() {
        if (!isLoading) {
            photos.clear()
            page = 1
            viewState.addNewItems()
            getFirstPhotos(isNeedSwipeRefresh = true)
        } else {
            viewState.changeSwipeRefreshState(false)
        }
    }

    fun onImageClicked(item: PhotoModel) {
        viewState.navigateToImageDetailFragment(item)
    }

    fun getPhotos() {
        if (isLastPage || isLoading) {
            return
        }

        photoGateway.getPhotos(new, popular, page, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.changeProgressViewState(true)
                isLoading = true
                viewState.changePlaceholderVisibility(true)
            }
            .doOnSuccess {
                page++
            }
            .doFinally {
                viewState.changeProgressViewState(false)
                isLoading = false
            }
            .subscribe({ response ->
                photos.addAll(response.data)
                viewState.addExtraItems(photos.size - response.data.size, response.data.size)
            }, {
                it.printStackTrace()
                photos.clear()
                viewState.changeProgressViewState(false)
                viewState.changePlaceholderVisibility(false)
            })
            .let(compositeDisposable::add)
    }

    private fun getFirstPhotos(isNeedSwipeRefresh: Boolean) {

        photoGateway.getPhotos(new, popular, page, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading = true
                if (isNeedSwipeRefresh) {
                    viewState.changeSwipeRefreshState(true)
                }
                viewState.changePlaceholderVisibility(true)
            }
            .doOnSuccess {
                page++
            }
            .doFinally {
                isLoading = false
                if (isNeedSwipeRefresh) {
                    viewState.changeSwipeRefreshState(false)
                }
            }
            .subscribe({
                photos.addAll(it.data as ArrayList<PhotoModel>)
                viewState.addNewItems()
            }, {
                it.printStackTrace()
                photos.clear()
                viewState.changeProgressViewState(false)
                viewState.changePlaceholderVisibility(false)
            })
            .let(compositeDisposable::add)
    }

    fun getData (name: String) {
        photos.clear()
        getSearchPhotos(name)
    }

    private fun getSearchPhotos (name: String) {
        photoGateway.getSearchablePhotos(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading = true
                viewState.changePlaceholderVisibility(true)
            }
            .doOnSuccess {
                page++
            }
            .doFinally {
                isLoading = false
            }
            .subscribe({
                photos.addAll(it.data as ArrayList<PhotoModel>)
                viewState.addNewItems()
            }, {
                it.printStackTrace()
                photos.clear()
                viewState.changeProgressViewState(false)
                viewState.changePlaceholderVisibility(false)
            })
            .let(compositeDisposable::add)
    }

}