package com.example.secondgallery.presentation.addPhoto

import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.R
import com.example.secondgallery.presentation.basemvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import okhttp3.MultipartBody
import javax.inject.Inject

@InjectViewState
class AddPhotoPresenter @Inject constructor(private val photoGateway: PhotoGateway) :
    BasePresenter<AddPhotoView>() {

    var photoTypeNew: Boolean = false
    var photoTypePopular: Boolean = false

    fun postPhoto(
        file: MultipartBody.Part, name: String, description: String,
        dateCreate: String, new: Boolean, popular: Boolean
    ) {
        photoGateway.postMediaObject(
            file
        ).flatMap {
            photoGateway.postPhoto(
                name,
                dateCreate,
                description,
                new,
                popular,
                "api/media_objects/" + it.id,
            )
        }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                viewState.showToast(R.string.image_uploaded)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)
    }


}