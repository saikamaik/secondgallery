package com.example.domain.entity.login

import java.util.*
import javax.management.monitor.StringMonitor

data class User(
    val id: Int?,
    val email: String?,
    val username: String,
    val birthday: Date?,
    val password: String?
)
