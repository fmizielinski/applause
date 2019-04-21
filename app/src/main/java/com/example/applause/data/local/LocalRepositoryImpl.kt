package com.example.applause.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import io.reactivex.Completable
import io.reactivex.Single

class LocalRepositoryImpl(private val preferences: SharedPreferences) : LocalRepository {

    private val ACCESS_TOKEN = "ACCESS_TOKEN"

    override val clientId: Single<String> = Single.just("04ecfc6e1af2c64588a9")

    override val clientSecret: Single<String> = Single.just("71a334ff6f003d3046aaf3a9ff412e750ba56644")

    override val redirectUri: Single<String> = Single.just("my://redirecturi")

    override val accessToken: Single<String>
        get() = Single.fromCallable {
            preferences.getString(ACCESS_TOKEN, "")
        }

    override fun storeAccessToken(token: String): Completable =
        Completable.fromAction {
            preferences.edit {
                putString(ACCESS_TOKEN, token)
            }
        }
}