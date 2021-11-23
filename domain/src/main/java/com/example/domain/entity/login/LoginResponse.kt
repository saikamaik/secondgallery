package com.example.domain.entity.login

data class LoginResponse(
    var accessToken: String,
    var expiresIn: Int,
    var tokenType: String,
    var refreshToken: String,
    var statusCode: Int?
)
