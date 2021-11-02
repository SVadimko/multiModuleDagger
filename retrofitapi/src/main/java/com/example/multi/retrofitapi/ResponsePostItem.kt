package com.example.multi.retrofitapi

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostItem(
    val body: String = "Body",
    val id: Int = 0,
    val title: String = "Title",
    val userId: Int = 0
)