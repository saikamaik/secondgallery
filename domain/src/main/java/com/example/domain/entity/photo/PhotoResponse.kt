package com.example.domain.entity.photo


data class PhotoResponse(
    val totalItems: Int,
    val itemsPerPage: Int,
    val countOfPages: Int,
    val data: List<PhotoModel>
)