package com.example.secondgallery.authorization

import android.annotation.SuppressLint
import com.example.domain.gateway.LoginGateway
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val loginGateway: Lazy<LoginGateway>,
    private val sessionManager: SessionManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken: String? = sessionManager.fetchAuthToken()
        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null
        }
        synchronized(this) {

            refreshToken()

            val newAccessToken: String? = sessionManager.fetchAuthToken()

            if (accessToken != newAccessToken) {
                return newAccessToken?.let { newRequestWithAccessToken(response.request(), it) }
            }

            val updatedAccessToken: String? = refreshToken()
            return updatedAccessToken?.let { newRequestWithAccessToken(response.request(), it) }
        }
    }

    private fun isRequestWithAccessToken(response: Response): Boolean {
        val header = response.request().header("Authorization")
        return header != null && header.startsWith("Bearer")
    }

    private fun newRequestWithAccessToken(
        request: Request,
        accessToken: String
    ): Request? {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

    @SuppressLint("CheckResult")
    private fun refreshToken(): String? {
        loginGateway.get().getRefresh(
            sessionManager.fetchClientId(),
            sessionManager.fetchRefreshToken(),
            sessionManager.fetchClientSecret()
        ).subscribe({
            sessionManager.saveAuthToken(it.refresh_token)
            sessionManager.saveAccessToken(it.access_token)
        }, {
            it.printStackTrace()
        })
        return sessionManager.fetchAuthToken()
    }

}