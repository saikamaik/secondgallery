package com.example.gateway.repository

import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.gateway.LoginGateway
import com.example.gateway.remoteDataSource.GalleryApi
import io.reactivex.Single
import retrofit2.Call

class RetrofitLoginGateway(private val api: GalleryApi): LoginGateway {

    override fun getLogin(client_id: String, username: String, password: String?, client_secret: String?): Single<LoginResponse> {
        return api.getLogin(client_id, username, password, client_secret)
    }

    override fun postClient(id: Int?, name: String, randomId: String?, secret: String?, allowedGrantTypes: List<String>) : Single<Client> {
        return api.postClient(id, name, randomId, secret, allowedGrantTypes)
    }
}