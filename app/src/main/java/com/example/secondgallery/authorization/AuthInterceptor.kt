package com.example.secondgallery.authorization

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.example.secondgallery.App
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
            200 -> {
            }
            400 -> {

            }
            401 -> {
                    val newToken: String? = sessionManager.fetchAuthToken()
                        if (newToken != null) {
                            val newRequest =  chain.request().newBuilder()
                                .addHeader("Authorization", newToken)
                                .build()
                            return chain.proceed(newRequest)
                        }
            }
            404 -> {
                //Show NotFound Message
            }
        }

        return response
    }

}