package com.example.secondgallery.presentation.newPhotos

import com.example.secondgallery.presentation.basemvp.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface NewView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getSearchablePhotos(name: String)

}