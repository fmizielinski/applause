package com.example.applause.di

import com.example.applause.domain.usecase.GetReposUseCase
import com.example.applause.domain.usecase.SearchUseCase
import com.example.applause.view.main.repos.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DomainModule {

    val module = module {

        //region viewmodel

        viewModel { ReposViewModel(get(), get()) }

        //endregion viewmodel

        //region network

        factory { GetReposUseCase(get()) }
        factory { SearchUseCase() }

        //endregion network
    }
}