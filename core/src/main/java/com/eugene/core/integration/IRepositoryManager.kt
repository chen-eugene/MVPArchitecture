package com.eugene.core.integration

import android.content.Context

interface IRepositoryManager {

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     */
    fun <T> obtainRetrofitService(service: Class<T>): T

    /**
     * 根据传入的 Class 获取对应的 RxCache service
     */
    fun <T> obtainCacheService(cache: Class<T>): T

    fun clearAllCache()

    fun getContext(): Context

}