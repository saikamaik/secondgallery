package com.example.gateway.remoteDataSource

import com.example.domain.entity.login.Client
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import com.example.domain.entity.login.UserPassword
import com.example.domain.entity.photo.PhotoResponse
import com.example.domain.entity.photo.Image
import com.example.domain.entity.photo.PostPhotoModel
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface GalleryApi {

    @GET("/oauth/v2/token?grant_type=password")
    fun getLogin(
        @Query("client_id") client_id: String, @Query("username") username: String,
        @Query("password") password: String?, @Query("client_secret") client_secret: String?
    ): Single<LoginResponse>

    @GET("/oauth/v2/token?grant_type=refresh_token&refresh_token=")
    fun getRefresh(
        @Query("client_id") client_id: String?, @Query("refresh_token") refresh_token: String?,
        @Query("client_secret") client_secret: String?
    ): Single<LoginResponse>

    @Headers("Content-type: application/json")
    @POST("/api/users")
    fun postUser(
        @Body body: User
    ): Single<User>

    @Headers("Content-type: application/json")
    @POST("/api/clients")
    fun postClient(
        @Body body: Client
    ): Single<Client>

    @GET("/api/photos?limit=10")
    fun getPhotos(
        @Query("new") new: String?, @Query("popular") popular: String?,
        @Query("page") page: Int, @Query("user.id") user: Int?
    ): Single<PhotoResponse>

    @Headers("Content-Type: application/json")
    @PUT("/api/users/{id}")
    fun editUser(
        @Path("id") id: Int, @Body body: User
    ): Single<User>

    @GET("/api/users/current")
    fun getCurrentUser(): Single<User>

    @Headers("Content-Type: application/json")
    @PUT("/api/users/update_password/{id}")
    fun changePassword(
        @Path("id") id: Int, @Body body: UserPassword
    ): Single<UserPassword>

    @DELETE("/api/users/{id}")
    fun deleteUser(
        @Path("id") id: Int
    ): Call<Void>

    @DELETE("/api/clients/{id}")
    fun deleteClient(
        @Path("id") id: Int
    ): Call<Void>

    @GET("/api/photos")
    fun getSearchablePhotos(
        @Query("name") name: String, @Query("page") page: Int
    ): Single<PhotoResponse>

    @Multipart
    @POST("/api/media_objects")
    fun postMediaObject(
        @Part file: MultipartBody.Part
    ): Single<Image>

    @POST("/api/photos")
    fun postPhoto(
        @Body body: PostPhotoModel
    ): Single<PostPhotoModel>

    @GET("{link}")
    fun getUser(
        @Path ("link") link: String
    ) : Single<User>

}