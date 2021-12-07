package com.example.gateway.remoteDataSource

import com.example.domain.entity.APIResponse
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface GalleryApi {

    @GET("/oauth/v2/token?grant_type=password")
    fun getLogin(
        @Query("client_id") client_id: String, @Query("username") username: String,
        @Query("password") password: String?, @Query("client_secret") client_secret: String?
    ): Single<LoginResponse>

    @POST("/auth_tokens")
    fun signIn(@Body info: AuthModel): Call<ResponseBody>

    @GET("/oauth/v2/token?grant_type=refresh_token&refresh_token=")
    fun getRefresh(
        @Query("client_id") client_id: String, @Query("refresh_token") refresh_token: String,
        @Query("client_secret") client_secret: String
    )

    @Headers("Content-type: application/json")
    @POST("/api/users")
    fun postUser(
        @Body body: User
    ) : Single<User>

    @Headers("Content-type: application/json")
    @POST("/api/clients")
    fun postClient(
        @Body body: Client
    ): Single<Client>

    @GET("/api/photos?limit=10")
    fun getPhotos(
        @Query("new") new: String?, @Query("popular") popular: String?,
        @Query("page") page: Int, @Query("username") user: String?
    ): Single<APIResponse>

    @DELETE("/api/users/")
    fun deleteUser(
        @Query("id") id: Int
    )

    @PUT("/api/users/")
    fun editUser(
        @Query("id") id: Int, @Body body: User
    ) : Single<User>

    @GET("/api/users/current")
    fun getCurrentUser() : Single<User>

    @PUT("/api/users/update_password/")
    fun changePassword(
        @Query ("id") id: Int, @Body body: User
    ) : Single<User>

    @GET("/api/photos?limit=10")
    fun getSearchablePhotos(
        @Query("name") name: String
    ) : Single<APIResponse>
}