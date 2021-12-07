package com.example.secondgallery.presentation.newPhotos

import android.app.Application
import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewPresenter @Inject constructor(photoGateway: PhotoGateway) :
    BasePresenter<NewView>(context = Application(), "true", null, null, photoGateway)