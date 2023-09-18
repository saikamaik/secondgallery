package com.example.secondgallery.presentation.profile

import com.example.domain.entity.login.User
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView : BasePhotoView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUpUI(user: User)

}