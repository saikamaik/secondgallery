package com.example.secondgallery.authActivity.signin

import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.R
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.basemvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SignInPresenter @Inject constructor(
    private val loginGateway: LoginGateway,
    private val sessionManager: SessionManager
) : BasePresenter<SignInView>() {

    fun authUser(user: User) {

        loginGateway.postClient(
            user.username
        ).doOnSuccess {
            sessionManager.saveClientInfo("${it.id}_${it.randomId}", it.secret!!)
        }
            .flatMap {
                loginGateway.getLogin(
                    "${it.id}_${it.randomId}",
                    user.username,
                    user.password!!,
                    it.secret
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                viewState.showToast(R.string.error_sign_in)
            }
            .doOnSuccess {
                viewState.navigateToHomeFragment()
                getCurrentUser()
            }
            .subscribe({
                sessionManager.saveAccessToken(it.access_token)
                sessionManager.saveAuthToken(it.refresh_token)
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)

    }

    private fun getCurrentUser() {
        loginGateway.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                sessionManager.saveId(id = it.id!!)
            }
            .subscribe({

            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)
    }
}