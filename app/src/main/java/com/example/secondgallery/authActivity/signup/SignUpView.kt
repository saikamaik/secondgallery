package com.example.secondgallery.authActivity.signup

import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SignUpView : BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToHomeFragment()

}