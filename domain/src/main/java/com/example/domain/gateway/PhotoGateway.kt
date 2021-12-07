package com.example.domain.gateway

import com.example.domain.entity.APIResponse
import io.reactivex.Single

interface PhotoGateway {

    fun getPhotos(new: String?, popular: String?, page: Int, user: String?): Single<APIResponse>

    fun getSearchablePhotos(name: String) : Single<APIResponse>
}