package com.eugene.core.integration

import android.content.Context

class RepositoryManagerImpl : IRepositoryManager {


    override fun <T> obtainRetrofitService(service: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> obtainCacheService(cache: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContext(): Context {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}