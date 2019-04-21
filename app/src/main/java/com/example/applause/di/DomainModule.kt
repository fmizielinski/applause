package com.example.applause.di

import com.example.applause.domain.usecase.local.GetAuthorizeUrlUseCase
import com.example.applause.domain.usecase.local.ParseRedirectDataUseCase
import com.example.applause.domain.usecase.local.StoreAccessTokenUseCase
import com.example.applause.domain.usecase.network.GetAccessTokenUseCase
import com.example.applause.view.main.MainViewModel
import com.example.applause.view.main.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DomainModule {

    val module = module {

        //region viewmodel

        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel(get(), get(), get()) }

        //endregion viewmodel

        //region local

        factory { GetAuthorizeUrlUseCase(get()) }
        factory { ParseRedirectDataUseCase(get()) }
        factory { StoreAccessTokenUseCase(get()) }

        //endregion local

        //region network

        factory { GetAccessTokenUseCase(get(), get()) }

        //endregion network
    }
}