package com.example.secondgallery.presentation.settings

import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.authorization.SessionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(private val loginGateway: LoginGateway) :
    MvpPresenter<SettingsView>() {

    private lateinit var sessionManager: SessionManager
    private val compositeDisposable = CompositeDisposable()
    private lateinit var currentUser: User

    fun getCurrentUser() {

        loginGateway.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.setUpUI(it)
            }
            .subscribe( {

            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)

    }

    fun editUser(user: User) {

        loginGateway.editUser(id = user.id!!, user.email, user.username, user.birthday)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)

    }

}