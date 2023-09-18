package com.example.secondgallery.authActivity.signin

import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SignInView : BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToHomeFragment()

}