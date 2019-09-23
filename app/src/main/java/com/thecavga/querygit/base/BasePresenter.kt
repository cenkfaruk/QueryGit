package com.thecavga.querygit.base


import android.os.Bundle
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

abstract class BasePresenter<T : BaseView> {

    private var view: WeakReference<T>? = null
    protected var isViewAlive = AtomicBoolean()

    fun getView(): T? {
        return view?.get()
    }

    fun setView(view: T) {
        this.view = WeakReference(view)
    }

    open fun initialize(extras: Bundle?) {}

    fun start() {
        isViewAlive.set(true)
    }

    fun finalizeView() {
        isViewAlive.set(false)
    }

}