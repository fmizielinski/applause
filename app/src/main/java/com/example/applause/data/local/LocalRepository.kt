package com.example.applause.data.local

import io.reactivex.Completable
import io.reactivex.Single

interface LocalRepository {

    val clientId: Single<String>

    val clientSecret: Single<String>

    val redirectUri: Single<String>

    val accessToken: Single<String>

    fun storeAccessToken(token: String): Completable
}