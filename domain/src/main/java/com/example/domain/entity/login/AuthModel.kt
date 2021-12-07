package com.example.domain.entity.login

data class AuthModel(
    var client_id: String,
    var username: String,
    var password: String,
    var client_secret: String?
)