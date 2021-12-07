package com.example.secondgallery.presentation.popularPhotos

import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PopularView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getSearchablePhotos(name: String)

}