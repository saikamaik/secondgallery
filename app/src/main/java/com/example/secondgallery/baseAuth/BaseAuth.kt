package com.example.secondgallery.baseAuth
//
//import android.app.Application
//import com.example.domain.entity.login.AuthModel
//import com.example.domain.entity.login.User
//import com.example.domain.gateway.LoginGateway
//import com.example.secondgallery.authorization.SessionManager
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import javax.inject.Inject
//
//class BaseAuth (
//        private val loginGateway: LoginGateway
//    ) {
//
//        private var sessionManager: SessionManager = SessionManager(context = Application())
//        private val compositeDisposable = CompositeDisposable()
//
//        fun postClient(user: User) {
//
//            var authModel: AuthModel
//
//            loginGateway.postClient(
//                user.username
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSuccess {
//
//                }
//                .subscribe( {
//                    authModel =
//                        AuthModel("${it.id}_${it.randomId}", user.username, user.password, it.secret)
//                    login(authModel)
//                }, {
//                    it.printStackTrace()
//                }).let(compositeDisposable::add)
//
//        }
//
//        private fun login(authModel: AuthModel) {
//            loginGateway.getLogin(
//                authModel.client_id,
//                authModel.username,
//                authModel.password,
//                authModel.client_secret
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSuccess {
//                    sessionManager.saveAuthToken(it.accessToken)
//                }
//                .subscribe()
//
//        }
//
//        public fun postUser(user: User) {
//
//            loginGateway.postUser(
//                user.email,
//                user.username,
//                user.password,
//                user.birthday,
//            ).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSuccess {
//                    postClient(it)
//                }
//                .subscribe( {
//
//                }, {
//                    it.printStackTrace()
//                }).let(compositeDisposable::add)
//
//        }
//
//    }