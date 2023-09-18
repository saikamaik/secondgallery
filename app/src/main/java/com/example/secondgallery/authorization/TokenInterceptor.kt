package com.example.secondgallery.authorization

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer ${sessionManager.fetchAuthToken()}")

        val request: Request = chain.request()
        val response = chain.proceed(request)
        val requestOauth: Boolean = request.url().toString().contains("oauth")

        if (!requestOauth) {
            when (response.code()) {
                409 -> {
                    response.close()
                    val newToken: String? = sessionManager.fetchAuthToken()
                    if (newToken != null) {
                        val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer $newToken")
                            .build()
                        response.close()
                        return chain.proceed(newRequest)
                    }
                }
                401 -> {
                    response.close()
                    val newToken: String? = sessionManager.fetchAuthToken()
                    if (newToken != null) {
                        val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer $newToken")
                            .build()
                        response.close()
                        return chain.proceed(newRequest)
                    }
                }
            }
        }
        return response
    }
}