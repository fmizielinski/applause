package com.example.applause.data.network

import com.example.applause.data.network.entity.AccessToken
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizeService {

    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    @Headers(
        "Accept: application/json"
    )
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Single<AccessToken>
}