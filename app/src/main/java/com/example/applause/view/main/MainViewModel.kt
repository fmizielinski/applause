package com.example.applause.view.main

import android.net.Uri
import com.example.applause.base.BaseViewModel
import com.example.applause.common.runAsyncReturnOnMain
import com.example.applause.domain.usecase.local.ParseRedirectDataUseCase
import com.example.applause.domain.usecase.local.StoreAccessTokenUseCase
import com.example.applause.domain.usecase.network.GetAccessTokenUseCase
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.CompletableSubject
import timber.log.Timber

class MainViewModel(
    private val parseRedirectDataUseCase: ParseRedirectDataUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val storeAccessTokenUseCase: StoreAccessTokenUseCase
) : BaseViewModel() {

    val loggedIn: Completable
        get() = loggedInSubject

    private val loggedInSubject = CompletableSubject.create()

    fun handleRedirectData(uri: Uri) {
        parseRedirectDataUseCase.execute(uri)
            .filter(String::isNotEmpty)
            .flatMapSingle(getAccessTokenUseCase::execute)
            .flatMapCompletable(storeAccessTokenUseCase::execute)
            .runAsyncReturnOnMain()
            .subscribe(loggedInSubject::onComplete, Timber::e)
            .addTo(compositeDisposable)
    }
}