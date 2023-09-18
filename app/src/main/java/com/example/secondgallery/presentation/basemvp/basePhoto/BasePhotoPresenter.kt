package com.example.secondgallery.presentation.basemvp.basePhoto

import com.example.domain.entity.photo.PhotoModel
import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BasePhotoPresenter<V : BasePhotoView>(
    private var new: Boolean?,
    private var popular: Boolean?,
    private var user: Int?,
    private val photoGateway: PhotoGateway
) : BasePresenter<V>() {

    private var isLoading = false
    private var photos: ArrayList<PhotoModel> = arrayListOf()
    private var page: Int = 1
    private var localName: String? = null
    private var isLastPage = false

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
            localName = null
            getFirstPhotos(isNeedSwipeRefresh = true)
        } else {
            viewState.changeSwipeRefreshState(false)
        }
    }

    fun onImageClicked(item: PhotoModel) {
        if (item.user != null) {
            getPhotoUser(item, item.user!!)
        } else
            viewState.navigateToImageDetailFragment(item, null)
    }

    fun checkSearch() {
        if (localName == null || localName == "") {
            getPhotos()
        } else getSearchPhotos(localName!!)
    }

    private fun getPhotos() {
        if (isLastPage || isLoading) {
            return
        }

        photoGateway.getPhotos(new.toString(), popular.toString(), page, user)
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

        photoGateway.getPhotos(new.toString(), popular.toString(), page, user)
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

    fun getSearchPhotos(name: String) {
        localName = name
        photos.clear()
        page = 1

        if (isLastPage || isLoading) {
            return
        } else if (name == "") {
            getFirstPhotos(isNeedSwipeRefresh = true)
            return
        }

        photoGateway.getSearchablePhotos(name, page)
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
                viewState.addNewSearchItems()
            }, {
                it.printStackTrace()
                localName = null
                photos.clear()
                viewState.changeProgressViewState(false)
                viewState.changePlaceholderVisibility(false)
            })
            .let(compositeDisposable::add)
    }

    fun photoSize(): Int {
        return photos.size
    }

    private fun getPhotoUser(item: PhotoModel, link: String) {

        photoGateway.getPhotoUser(link.drop(1))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.navigateToImageDetailFragment(item, it.username)
            }
            .subscribe({

            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)

    }

}