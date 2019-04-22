package com.example.applause.view.main.repos

import com.example.applause.base.BaseViewModel
import com.example.applause.common.runAsyncReturnOnMain
import com.example.applause.domain.usecase.GetReposUseCase
import com.example.applause.view.main.repos.adapter.RepoItemViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class ReposViewModel(private val getReposUseCase: GetReposUseCase) : BaseViewModel() {

    val repos: Observable<List<RepoItemViewModel>>
        get() = reposSubject

    private val reposSubject: BehaviorSubject<List<RepoItemViewModel>> = BehaviorSubject.create()

    fun loadRepos() {
        if (reposSubject.hasValue())
            return
        getReposUseCase.execute()
            .runAsyncReturnOnMain()
            .map { list -> list.map { RepoItemViewModel(it) } }
            .subscribe(reposSubject::onNext, Timber::e)
            .addTo(compositeDisposable)
    }
}