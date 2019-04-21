package com.example.applause.view.main.login

import com.example.applause.base.BaseViewModel
import com.example.applause.common.runAsyncReturnOnMain
import com.example.applause.domain.usecase.local.GetAuthorizeUrlUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class LoginViewModel(private val getAuthorizeUrlUseCase: GetAuthorizeUrlUseCase) : BaseViewModel() {

    val authorizeUrl: Observable<String>
        get() = authorizeUrlSubject

    private val authorizeUrlSubject: PublishSubject<String> = PublishSubject.create()

    fun login() {
        getAuthorizeUrlUseCase.execute()
            .runAsyncReturnOnMain()
            .subscribe(authorizeUrlSubject::onNext, Timber::e)
            .addTo(compositeDisposable)
    }
}