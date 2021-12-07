package com.example.secondgallery.authorization

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken().let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        val request: Request = chain.request()
        val response = chain.proceed(request)

        when (response.code()) {
            401 -> {
                val newToken: String? = sessionManager.fetchAuthToken()
                if (newToken != null) {
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", newToken)
                        .build()
                    return chain.proceed(newRequest)
                }
            }
        }

        return response
    }

}