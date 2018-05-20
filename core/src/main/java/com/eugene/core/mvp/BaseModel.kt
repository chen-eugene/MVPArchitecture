package com.eugene.core.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.eugene.core.integration.IRepositoryManager

abstract class BaseModel(var repositoryManager: IRepositoryManager?) : IModel, LifecycleObserver {


    /**
     * 监听IView生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
        onDestroy()
    }


    override fun onDestroy() {
        repositoryManager = null
    }


}