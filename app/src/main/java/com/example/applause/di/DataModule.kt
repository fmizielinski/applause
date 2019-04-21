package com.example.applause.di

import android.content.Context
import com.example.applause.data.local.LocalRepository
import com.example.applause.data.local.LocalRepositoryImpl
import com.example.applause.data.network.AuthorizeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

    val module = module {
        //region local

        single {
            get<Context>().getSharedPreferences(
                DataModule::class.java.simpleName,
                Context.MODE_PRIVATE
            )
        }

        factory<LocalRepository> { LocalRepositoryImpl(get()) }

        //endregion local

        //region network

        factory<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }

        factory<Converter.Factory> {
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            )
        }

        single {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        }

        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://github.com/")
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()

            retrofit.create(AuthorizeService::class.java)
        }

        //endregion network
    }
}