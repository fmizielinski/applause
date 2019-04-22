package com.example.applause.domain.usecase

import com.example.applause.data.network.ApiService
import com.example.applause.data.network.entity.Repo
import com.example.applause.domain.entity.RepoUI
import com.example.applause.domain.entity.toRepoUI
import io.reactivex.Single

class GetReposUseCase(private val apiService: ApiService) {

    fun execute(): Single<List<RepoUI>> =
        apiService.getRepositories()
            .map { list ->
                list.subList(0, 10)
                    .map(Repo::toRepoUI)
            }
}