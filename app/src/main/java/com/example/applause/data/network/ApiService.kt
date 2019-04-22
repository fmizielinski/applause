package com.example.applause.data.network

import com.example.applause.data.network.entity.Repo
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/users/ApplauseOSS/repos")
    fun getRepositories(): Single<List<Repo>>
}