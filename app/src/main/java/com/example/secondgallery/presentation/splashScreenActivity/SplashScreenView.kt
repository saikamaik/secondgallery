package com.example.secondgallery.presentation.splashScreenActivity

import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SplashScreenView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun navigateToActivity(activity: MvpAppCompatActivity)
}