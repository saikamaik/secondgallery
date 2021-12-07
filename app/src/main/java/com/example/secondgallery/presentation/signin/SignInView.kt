package com.example.secondgallery.presentation.signin

import com.example.domain.entity.PhotoModel
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SignInView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToHomeFragment()

}