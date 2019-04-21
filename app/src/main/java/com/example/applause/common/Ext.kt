package com.example.applause.common

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Single<T>.runAsyncReturnOnMain(): Single<T> =
    this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun Completable.runAsyncReturnOnMain(): Completable =
    this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.runAsyncReturnOnMain(): Observable<T> =
    this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun View.setOnClickListener(listener: () -> Unit) {
    setOnClickListener { listener.invoke() }
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer<T> { t -> observer.invoke(t) })
}