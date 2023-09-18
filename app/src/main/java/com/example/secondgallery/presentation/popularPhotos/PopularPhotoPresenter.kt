package com.example.secondgallery.presentation.popularPhotos

import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoPresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class PopularPhotoPresenter @Inject constructor(photoGateway: PhotoGateway) :
    BasePhotoPresenter<PopularPhotoView>(null, true, null, photoGateway)