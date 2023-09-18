package com.example.secondgallery.presentation.profile

import android.content.SharedPreferences
import com.example.domain.gateway.LoginGateway
import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoPresenter
import com.example.secondgallery.utils.Const.USER_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(
    photoGateway: PhotoGateway, private val loginGateway: LoginGateway,
    sharedPreferences: SharedPreferences?
) :
    BasePhotoPresenter<ProfileView>(
        null, null,
        sharedPreferences?.getString(USER_ID, null)?.toInt(), photoGateway
    ) {

    fun getCurrentUser() {

        loginGateway.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setUpUI(it)
            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)
    }
}