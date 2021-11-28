package com.example.domain.entity

import java.util.*

data class PhotoModel(
    val id: Int,
    val name: String?,
    val dateCreate: String,
    val description: String?,
    val new: Boolean,
    val popular: Boolean,
    val image: Image,
    val user: String
)