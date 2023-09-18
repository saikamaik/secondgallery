package com.example.secondgallery.presentation.splashScreenActivity

import com.example.secondgallery.authActivity.secondActivity.SecondActivity
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.mainActivity.MainActivity
import moxy.MvpPresenter
import javax.inject.Inject

class SplashScreenPresenter @Inject constructor(
    private val sessionManager: SessionManager
) : MvpPresenter<SplashScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkForAccessToken()
    }

    private fun checkForAccessToken() {
        if (sessionManager.fetchAuthToken() == null) {
            viewState.navigateToActivity(SecondActivity())
        } else {
            viewState.navigateToActivity(MainActivity())
        }
    }

}