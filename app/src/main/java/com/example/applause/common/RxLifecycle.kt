package com.example.applause.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun <T> Observable<T>.subscribe(
    lifecycleOwner: LifecycleOwner,
    onNext: (T) -> Unit
): Disposable {
    val disposable = subscribe(onNext)
    lifecycleOwner.lifecycle.addObserver(RxLifecycleObserver(disposable))
    return disposable
}

fun Completable.subscribe(
    lifecycleOwner: LifecycleOwner,
    onNext: () -> Unit
): Disposable {
    val disposable = subscribe(onNext)
    lifecycleOwner.lifecycle.addObserver(RxLifecycleObserver(disposable))
    return disposable
}

private class RxLifecycleObserver(private val disposable: Disposable) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        disposable.dispose()
        Timber.d("DISPOSED!!!")
    }
}