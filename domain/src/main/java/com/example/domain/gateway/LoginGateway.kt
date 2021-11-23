package com.example.domain.gateway

import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import io.reactivex.Single
import retrofit2.Call

interface LoginGateway {

    fun getLogin(client_id: String, username: String, password: String?, client_secret: String?) : Single<LoginResponse>

    fun postClient(id: Int?, name: String, randomId: String?, secret: String?, allowedGrantTypes: List<String>) : Single<Client>
}