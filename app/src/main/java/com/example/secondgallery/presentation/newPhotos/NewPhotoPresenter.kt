package com.example.secondgallery.presentation.newPhotos

import com.example.domain.gateway.PhotoGateway
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoPresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewPhotoPresenter @Inject constructor(photoGateway: PhotoGateway) :
    BasePhotoPresenter<NewPhotoView>(true, null, null, photoGateway)