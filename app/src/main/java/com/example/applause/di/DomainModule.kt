package com.example.applause.di

import com.example.applause.domain.entity.RepoUI
import com.example.applause.domain.usecase.GetReposUseCase
import com.example.applause.domain.usecase.SearchUseCase
import com.example.applause.view.main.details.DetailsViewModel
import com.example.applause.view.main.repos.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DomainModule {

    val module = module {

        //region viewmodel

        viewModel { ReposViewModel(get(), get()) }
        viewModel { (repo: RepoUI) -> DetailsViewModel(repo) }

        //endregion viewmodel

        //region network

        factory { GetReposUseCase(get()) }
        factory { SearchUseCase() }

        //endregion network
    }
}