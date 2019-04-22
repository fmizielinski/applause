package com.example.applause.data.network.entity

import com.squareup.moshi.Json

data class Owner(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)