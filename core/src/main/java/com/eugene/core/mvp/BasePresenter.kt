package com.eugene.core.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.simple.eventbus.EventBus

abstract class BasePresenter<M : IModel, V : IView>
    : LifecycleObserver, IPresenter {

    protected open val TAG by lazy {
        this::class.java.name
    }

    protected var view: V? = null
    protected var model: M? = null

    protected var compositeDisposable: CompositeDisposable? = null

    constructor(view: V) {
        this.view = view
        onStart()
    }

    constructor(model: M, view: V) : this(view) {
        this.model = model
    }

    override fun onStart() {
        //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
            if (model is LifecycleOwner) {
                (view as LifecycleOwner).lifecycle.addObserver(model as LifecycleObserver)
            }
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }

    }

    override fun useEventBus(): Boolean {
        return true
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务,避免内存泄漏
     * 目前框架已使用 {@link RxLifecycle} 避免内存泄漏,此方法作为备用方案
     */
    fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    fun unDisposable() {
        compositeDisposable?.clear()
    }


    /**
     * 监听IView生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用
        owner.lifecycle.removeObserver(this)
        onDestroy()
    }

    override fun onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
//        model?.onDestroy()
        model = null
        view = null
    }


}


