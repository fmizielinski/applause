package com.example.applause.domain.usecase.local

import com.example.applause.data.local.LocalRepository
import com.example.applause.data.network.entity.AccessToken
import io.reactivex.Completable

class StoreAccessTokenUseCase(private val repository: LocalRepository) {

    fun execute(accessToken: AccessToken): Completable =
        repository.storeAccessToken(accessToken.accessToken)
}