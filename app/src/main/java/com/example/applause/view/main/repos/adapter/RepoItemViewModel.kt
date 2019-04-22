package com.example.applause.view.main.repos.adapter

import com.example.applause.domain.entity.RepoUI
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RepoItemViewModel(private val repo: RepoUI) {

    val name = repo.name

    val language = repo.language

    val stars = repo.stars

    val onClick: Observable<RepoUI>
        get() = onClickSubject

    private val onClickSubject: PublishSubject<RepoUI> = PublishSubject.create()

    fun click() {
        onClickSubject.onNext(repo)
    }
}