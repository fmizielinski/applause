package com.example.applause.domain.entity

import android.os.Parcelable
import com.example.applause.data.network.entity.Repo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoUI(
    val name: String,
    val ownerName: String,
    val url: String,
    val description: String?,
    val stars: Int,
    val language: String
) : Parcelable

fun Repo.toRepoUI() =
        RepoUI(
            name,
            owner.login,
            htmlUrl,
            description,
            stargazersCount,
            language
        )