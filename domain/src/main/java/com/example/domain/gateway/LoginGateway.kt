package com.example.domain.gateway

import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import com.example.domain.entity.login.UserPassword
import io.reactivex.Single
import retrofit2.Call

interface LoginGateway {

    fun getLogin(
        client_id: String,
        username: String,
        password: String,
        client_secret: String?
    ): Single<LoginResponse>

    fun postClient(name: String): Single<Client>

    fun postUser(
        email: String?,
        username: String,
        password: String,
        birthday: String?
    ): Single<User>

    fun getCurrentUser(): Single<User>

    fun changePassword(id: Int, oldPassword: String, newPassword: String): Single<UserPassword>

    fun editUser(id: Int, email: String?, username: String, birthday: String?): Single<User>

    fun getRefresh(
        client_id: String?,
        refresh_token: String?,
        client_secret: String?
    ): Single<LoginResponse>

    fun deleteUser(id: Int): Call<Void>

    fun deleteClient(id: Int): Call<Void>

}