package com.example.applause.domain.usecase

import com.example.applause.domain.entity.RepoUI
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

class SearchUseCaseTest : KoinTest {

    val useCase: SearchUseCase by inject()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        startKoin {
            modules(
                module {
                    single { SearchUseCase() }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should return list with names matching given query`() {
        val list = listOf(
            RepoUI("qwerty", "a", "a", "a", 1, "a"),
            RepoUI("qwe", "a", "a", "a", 1, "a"),
            RepoUI("asdf", "a", "a", "a", 1, "a")
        )

        var observer = useCase.execute(list, "qwe").test()

        observer.assertValue { it.size == 2 }
        observer.assertValue { list ->
            list.all { it.name.contains("qwe", true) }
        }


        observer = useCase.execute(list, "qWe").test()

        observer.assertValue { it.size == 2 }
        observer.assertValue { list ->
            list.all { it.name.contains("qWe", true) }
        }


        observer = useCase.execute(list, "asdf").test()

        observer.assertValue { it.size == 1 }
        observer.assertValue { list ->
            list.all { it.name.contains("asdf", true) }
        }


        observer = useCase.execute(list, "ggg").test()

        observer.assertValue { it.isEmpty() }
    }
}