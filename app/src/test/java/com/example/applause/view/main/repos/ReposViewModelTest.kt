package com.example.applause.view.main.repos

import com.example.applause.data.network.ApiService
import com.example.applause.data.network.entity.Owner
import com.example.applause.data.network.entity.Repo
import com.example.applause.domain.usecase.GetReposUseCase
import com.example.applause.domain.usecase.SearchUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class ReposViewModelTest : KoinTest {

    val viewModel: ReposViewModel by inject()
    val apiService: ApiService by inject()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        startKoin {
            modules(
                module {
                    single { mock<ApiService>() }
                    single { GetReposUseCase(get()) }
                    single { SearchUseCase() }
                    single { ReposViewModel(get(), get()) }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should not call api on searching by name`() {
        val list = mutableListOf<Repo>()
        repeat(10) {
            list.add(Repo("a", Owner("a"), "a", "a", 1, "a"))
        }

        whenever(apiService.getRepositories()).thenReturn(Single.just(list))

        viewModel.loadRepos()
        viewModel.search("a")
        viewModel.search("b")
        viewModel.search("c")

        verify(apiService, times(1)).getRepositories()
    }

    @Test
    fun `should not call api without forcing refresh when data is loaded`() {
        val list = mutableListOf<Repo>()
        repeat(10) {
            list.add(Repo("a", Owner("a"), "a", "a", 1, "a"))
        }

        whenever(apiService.getRepositories()).thenReturn(Single.just(list))

        viewModel.loadRepos()
        viewModel.loadRepos()

        verify(apiService, times(1)).getRepositories()
    }

    @Test
    fun `should call api second time after forcing refresh when data is loaded`() {
        val list = mutableListOf<Repo>()
        repeat(10) {
            list.add(Repo("a", Owner("a"), "a", "a", 1, "a"))
        }

        whenever(apiService.getRepositories()).thenReturn(Single.just(list))

        viewModel.loadRepos()
        viewModel.loadRepos(true)

        verify(apiService, times(2)).getRepositories()
    }
}