package com.example.applause.view.main.repos

import com.example.applause.base.BaseViewModel
import com.example.applause.common.runAsyncReturnOnMain
import com.example.applause.domain.entity.RepoUI
import com.example.applause.domain.usecase.GetReposUseCase
import com.example.applause.domain.usecase.SearchUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class ReposViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    val repos: Observable<List<RepoUI>>
        get() = Observables.combineLatest(
            reposSubject,
            querySubject
        ).flatMap { (repos, query) -> searchUseCase.execute(repos, query) }

    val error: Observable<Unit>
        get() = errorSubject

    private val reposSubject: BehaviorSubject<List<RepoUI>> = BehaviorSubject.create()

    private val querySubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    private val errorSubject: PublishSubject<Unit> = PublishSubject.create()

    fun loadRepos(refresh: Boolean = false) {
        if (reposSubject.hasValue() && !refresh)
            return
        getReposUseCase.execute()
            .runAsyncReturnOnMain()
            .subscribe(reposSubject::onNext) {
                Timber.e(it)
                errorSubject.onNext(Unit)
            }
            .addTo(compositeDisposable)
    }

    fun search(query: String) {
        querySubject.onNext(query)
    }
}