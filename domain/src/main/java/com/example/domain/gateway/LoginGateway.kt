package com.example.domain.gateway

import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import io.reactivex.Single
import java.util.*

interface LoginGateway {

    fun getLogin(client_id: String, username: String, password: String, client_secret: String?) : Single<LoginResponse>

    fun postClient(name: String) : Single<Client>

    fun postUser(email: String?, username: String, password: String, birthday: Date?) : Single<User>

    fun getCurrentUser() : Single<User>

    fun editUser(id: Int, email: String?, username: String, birthday: Date?) : Single<User>
}