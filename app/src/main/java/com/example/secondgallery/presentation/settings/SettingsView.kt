package com.example.secondgallery.presentation.settings

import com.example.domain.entity.login.User
import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SettingsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUpUI(user: User)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUpListeners()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun navigateToWelcomeActivity()

}