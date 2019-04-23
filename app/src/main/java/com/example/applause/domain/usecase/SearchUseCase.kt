package com.example.applause.domain.usecase

import com.example.applause.domain.entity.RepoUI
import io.reactivex.Observable

class SearchUseCase {

    fun execute(list: List<RepoUI>, query: String): Observable<List<RepoUI>> =
        Observable.fromCallable {
            list.filter { it.name.contains(query, true) }
        }
}