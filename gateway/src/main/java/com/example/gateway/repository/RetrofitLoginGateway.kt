package com.example.gateway.repository

import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import com.example.domain.entity.login.UserPassword
import com.example.domain.gateway.LoginGateway
import com.example.gateway.remoteDataSource.GalleryApi
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class RetrofitLoginGateway @Inject constructor(private val api: GalleryApi) : LoginGateway {

    override fun getLogin(
        client_id: String,
        username: String,
        password: String,
        client_secret: String?
    ): Single<LoginResponse> {
        return api.getLogin(client_id, username, password, client_secret)
    }

    override fun postClient(
        name: String
    ): Single<Client> {
        return api.postClient(
            body = Client(
                null,
                name,
                null,
                null,
                listOf("password", "refresh_token")
            )
        )
    }

    override fun postUser(
        email: String?, username: String, password: String, birthday: String?
    ): Single<User> {
        return api.postUser(
            body = User(
                null,
                email,
                username,
                birthday,
                password
            )
        )
    }

    override fun editUser(
        id: Int,
        email: String?,
        username: String,
        birthday: String?
    ): Single<User> {
        return api.editUser(
            id,
            body = User(
                null,
                email,
                username,
                birthday,
                null
            )
        )
    }

    override fun changePassword(
        id: Int,
        oldPassword: String,
        newPassword: String
    ): Single<UserPassword> {
        return api.changePassword(
            id,
            UserPassword(oldPassword, newPassword)
        )
    }

    override fun getRefresh(
        client_id: String?,
        refresh_token: String?,
        client_secret: String?
    ): Single<LoginResponse> {
        return api.getRefresh(client_id, refresh_token, client_secret)
    }

    override fun getCurrentUser(): Single<User> {
        return api.getCurrentUser()
    }

    override fun deleteUser(id: Int): Call<Void> {
        return api.deleteUser(id)
    }

    override fun deleteClient(id: Int): Call<Void> {
        return api.deleteClient(id)
    }

}