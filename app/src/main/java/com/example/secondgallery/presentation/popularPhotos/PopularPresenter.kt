package com.example.secondgallery.presentation.popularPhotos

import android.app.Application
import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class PopularPresenter @Inject constructor(photoGateway: PhotoGateway) :
    BasePresenter<PopularView>(context = Application(), null, "true", null, photoGateway) {
}