package com.example.secondgallery.presentation.settings

import com.example.domain.entity.login.User
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SettingsView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUpUI(user:User)

}