package com.example.secondgallery.presentation.signin

import android.content.Context
import androidx.navigation.Navigation.findNavController
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.MainActivity
import com.example.secondgallery.R
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.di.RetrofitModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SignInPresenter @Inject constructor(
    private val loginGateway: LoginGateway
) :
    MvpPresenter<SignInView>() {

    private lateinit var retrofitModule: RetrofitModule
    private lateinit var sessionManager: SessionManager
    private val compositeDisposable = CompositeDisposable()

    // todo То же самое, что и в регистрации

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

}