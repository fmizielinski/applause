package com.example.applause.domain.usecase.local

import com.example.applause.data.local.LocalRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles

class GetAuthorizeUrlUseCase(private val repository: LocalRepository) {

    private val url = "https://github.com/login/oauth/authorize?client_id=%s&client_secret=%s"

    fun execute(): Single<String> =
            Singles.zip(
                repository.clientId,
                repository.clientSecret
            ) { clientId, clientSecret ->
                String.format(url, clientId, clientSecret)
            }
}