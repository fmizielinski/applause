package com.example.applause.domain.usecase.local

import android.net.Uri
import com.example.applause.data.local.LocalRepository
import io.reactivex.Single

class ParseRedirectDataUseCase(private val repository: LocalRepository) {

    fun execute(uri: Uri): Single<String> =
        repository.redirectUri
            .map { redirectUri ->
                if (!uri.toString().startsWith(redirectUri))
                    return@map ""

                val code = uri.getQueryParameter("code") ?: return@map ""

                code
            }
}