package com.example.applause.data.network.entity

import com.squareup.moshi.Json

data class AccessToken(
    @Json(name = "access_token")
    val accessToken: String,
    val scope: String,
    @Json(name = "token_type")
    val tokenType: String
)