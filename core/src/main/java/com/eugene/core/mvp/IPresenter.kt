package com.eugene.core.mvp

interface IPresenter {

    /**
     * 初始化操作
     */
    fun onStart()

    fun useEventBus():Boolean

    fun onDestroy()

}