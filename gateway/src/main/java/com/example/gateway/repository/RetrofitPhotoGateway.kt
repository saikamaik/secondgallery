package com.example.gateway.repository

import com.example.domain.entity.login.User
import com.example.domain.entity.photo.PhotoResponse
import com.example.domain.entity.photo.Image
import com.example.domain.entity.photo.PostPhotoModel
import com.example.domain.gateway.PhotoGateway
import com.example.gateway.remoteDataSource.GalleryApi
import io.reactivex.Single
import okhttp3.MultipartBody
import javax.inject.Inject

class RetrofitPhotoGateway @Inject constructor(
    private val api: GalleryApi
) : PhotoGateway {

    override fun getPhotos(
        new: String?,
        popular: String?,
        page: Int,
        user: Int?
    ): Single<PhotoResponse> {
        return api.getPhotos(new, popular, page, user)
    }

    override fun getSearchablePhotos(name: String, page: Int): Single<PhotoResponse> {
        return api.getSearchablePhotos(name, page)
    }

    override fun postMediaObject(file: MultipartBody.Part): Single<Image> {
        return api.postMediaObject(file)
    }

    override fun postPhoto(
        name: String,
        dateCreate: String,
        description: String?,
        new: Boolean,
        popular: Boolean,
        image: String
    ): Single<PostPhotoModel> {
        return api.postPhoto(PostPhotoModel(null, name, dateCreate, description, new, popular, image, null))
    }

    override fun getPhotoUser(link: String): Single<User> {
        return api.getUser(link)
    }
}
