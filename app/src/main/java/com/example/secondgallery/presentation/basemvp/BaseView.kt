package com.example.secondgallery.presentation.basemvp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showToast(id: Int)

}