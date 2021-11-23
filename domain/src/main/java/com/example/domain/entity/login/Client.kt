package com.example.domain.entity.login

data class Client (
    val id: Int?,
    val name: String,
    val randomId: String?,
    val secret: String?,
    val allowedGrantTypes: List<String>
    )