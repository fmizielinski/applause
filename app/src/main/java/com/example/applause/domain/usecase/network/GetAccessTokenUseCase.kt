package com.example.applause.domain.usecase.network

import com.example.applause.data.local.LocalRepository
import com.example.applause.data.network.AuthorizeService
import com.example.applause.data.network.entity.AccessToken
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles

class GetAccessTokenUseCase(
    private val service: AuthorizeService,
    private val repository: LocalRepository
) {

    fun execute(code: String): Single<AccessToken> =
        Singles.zip(
            repository.clientId,
            repository.clientSecret
        ).flatMap { (clientId, clientSecret) ->
            service.getAccessToken(clientId, clientSecret, code)
        }
}