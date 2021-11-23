package com.example.gateway.remoteDataSource

import com.example.domain.entity.APIResponse
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GalleryApi {

    @GET("/oauth/v2/token?grant_type=password")
    fun getLogin(
        @Query("client_id") client_id: String, @Query("username") username: String,
        @Query("password") password: String?, @Query("client_secret") client_secret: String?
    ) : Single<LoginResponse>

    @POST("auth_tokens")
    fun signIn(@Body info: AuthModel): Call<ResponseBody>


    @GET(" /oauth/v2/token?grant_type=refresh_token&refresh_token=")
    fun getRefresh(
        @Query("client_id") client_id: String, @Query("refresh_token") refresh_token: String,
        @Query("client_secret") client_secret: String
    )

    @POST("/api/users")
    fun postUser(

    )

    @POST("/api/clients")
    fun postClient(
        @Query("id") id: Int?, @Query("name") name: String,
        @Query("randomId") randomId: String?, @Query("secret") secret: String?,
        @Query("allowedGrantTypes") allowedGrantTypes: List<String>
    ) : Single<Client>

    @GET("photos?limit=10")
    fun getPhotos(
        @Query("new") new: String?, @Query("popular") popular: String?, @Query("page") page: Int
    ) : Single<APIResponse>
}