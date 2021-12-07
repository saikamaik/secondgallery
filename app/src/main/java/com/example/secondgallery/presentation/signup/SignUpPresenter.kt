package com.example.secondgallery.presentation.signup

import android.app.Application
import android.content.Context
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.signin.SignInPresenter
import com.example.secondgallery.presentation.signin.SignInView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SignUpPresenter @Inject constructor(
    private val loginGateway: LoginGateway
) :
    MvpPresenter<SignUpView>() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var sessionManager: SessionManager

    fun postClient(user: User, context: Context) {

        var authModel: AuthModel

        loginGateway.postClient(
            user.username
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {

            }
            .subscribe( {
                authModel =
                    AuthModel("${it.id}_${it.randomId}", user.username, user.password!!, it.secret)
                login(authModel, context)
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)

    }

    private fun login(authModel: AuthModel, context: Context) {

        loginGateway.getLogin(
            authModel.client_id,
            authModel.username,
            authModel.password,
            authModel.client_secret
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.navigateToHomeFragment()
            }
            .subscribe({
                sessionManager = SessionManager(context)
                sessionManager.saveAuthToken(it.refresh_token)
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)
    }

    fun postUser(user: User, context: Context) {

        loginGateway.postUser(
            user.email,
            user.username,
            user.password!!,
            user.birthday,
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                postClient(user, context)
            }
            .subscribe( {
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)

    }

}