package com.eugene.core.di.module

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import android.util.Log
import com.eugene.core.integration.IRepositoryManager
import com.eugene.core.module.cache.Cache
import com.eugene.core.module.cache.CacheType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Provides
    fun provideGson(application: Application, configuration: GsonConfiguration?): Gson {
        val builder = GsonBuilder()
        configuration?.configGson(application, builder)
        return builder.create()
    }

    @Binds
    abstract fun bindRepositoryManager(repositoryManager: IRepositoryManager): IRepositoryManager

    @Singleton
    @Provides
    fun provideExtras(cacheFactory: Cache.Factory): Cache<String, Any> {
        return cacheFactory.build(CacheType.EXTRAS)
    }

//    @Binds
//    @Named("ActivityLifecycle")
//    abstract fun bindActivityLifecycle(activityLifecycle: IActivityL): Application.ActivityLifecycleCallbacks

//    @Binds
//    @Named("ActivityLifecycleForRxLifecycle")
//    abstract fun bindActivityLifecycleForRxLifecycle(activityLifecycleForRxLifecycle: ActivityLifecycleForRxLifecycle): Application.ActivityLifecycleCallbacks

//    @Binds
//    abstract fun bindFragmentLifecycle(fragmentLifecycle: FragmentLifecycle): FragmentManager.FragmentLifecycleCallbacks

    @Singleton
    @Provides
    fun provideFragmentLifecycles(): List<FragmentManager.FragmentLifecycleCallbacks> {
        return ArrayList()
    }

    interface GsonConfiguration {
        fun configGson(context: Context, builder: GsonBuilder)
    }

}