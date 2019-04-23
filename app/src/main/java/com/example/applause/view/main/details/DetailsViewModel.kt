package com.example.applause.view.main.details

import com.example.applause.base.BaseViewModel
import com.example.applause.domain.entity.RepoUI
import io.reactivex.Single

class DetailsViewModel(private val repo: RepoUI) : BaseViewModel() {

    val data: Single<RepoUI> = Single.just(repo)
}