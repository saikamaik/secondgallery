package com.example.domain.entity.login

data class LoginResponse(
    var access_token: String,
    var expires_in: Int,
    var token_type: String,
    var refresh_token: String,
    var statusCode: Int?
)
