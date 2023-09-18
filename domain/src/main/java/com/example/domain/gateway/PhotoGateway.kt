package com.example.domain.gateway

import com.example.domain.entity.login.User
import com.example.domain.entity.photo.PhotoResponse
import com.example.domain.entity.photo.Image
import com.example.domain.entity.photo.PostPhotoModel
import io.reactivex.Single
import okhttp3.MultipartBody

interface PhotoGateway {

    fun getPhotos(new: String?, popular: String?, page: Int, user: Int?): Single<PhotoResponse>

    fun getSearchablePhotos(name: String, page: Int): Single<PhotoResponse>

    fun postMediaObject(file: MultipartBody.Part): Single<Image>

    fun postPhoto(name: String, dateCreate: String, description: String?, new: Boolean,
                  popular: Boolean, image: String) : Single<PostPhotoModel>

    fun getPhotoUser(link: String): Single<User>
}