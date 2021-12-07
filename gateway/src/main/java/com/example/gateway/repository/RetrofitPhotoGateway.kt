package com.example.gateway.repository

import com.example.domain.entity.APIResponse
import com.example.domain.gateway.PhotoGateway
import com.example.gateway.remoteDataSource.GalleryApi
import io.reactivex.Single
import javax.inject.Inject

class RetrofitPhotoGateway @Inject constructor(
    private val api: GalleryApi
) : PhotoGateway {

    override fun getPhotos(new: String?, popular: String?, page: Int, user: String?): Single<APIResponse> {
        return api.getPhotos(new, popular, page, user)
    }

    override fun getSearchablePhotos(name: String) : Single<APIResponse> {
        return api.getSearchablePhotos(name)
    }
}