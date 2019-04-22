package com.example.applause.data.network.entity

import com.squareup.moshi.Json

data class Repo(
    val name: String,
    val owner: Owner,
    @Json(name="html_url")
    val htmlUrl: String,
    val description: String?,
    @Json(name="stargazers_count")
    val stargazersCount: Int,
    val language: String
)