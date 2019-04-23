package com.example.applause.domain.usecase

import com.example.applause.data.network.ApiService
import com.example.applause.data.network.entity.Owner
import com.example.applause.data.network.entity.Repo
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

class GetReposUseCaseTest : KoinTest {

    val useCase: GetReposUseCase by inject()
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
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should give 10 records when api returns more`() {
        val list = mutableListOf<Repo>()

        repeat(12) {
            list.add(Repo("a", Owner("a"), "a", "a", 1, "a"))
        }

        whenever(apiService.getRepositories()).thenReturn(Single.just(list))

        val observer = useCase.execute().test()

        observer.assertComplete()
        observer.assertNoErrors()
        observer.assertValue { it.size == 10 }

        verify(apiService, times(1)).getRepositories()
    }
}