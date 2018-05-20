package com.eugene.core.di.module

import android.app.Application
import android.content.Context
import com.eugene.core.integration.IRepositoryManager
import com.eugene.core.integration.RepositoryManagerImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import io.rx_cache2.internal.RxCache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class ClientModule {

    @Binds
    fun provideRetrofit(application: Application, configuration: RetrofitConfiguration?,
                       builder: Retrofit.Builder, client: OkHttpClient, httpUrl: HttpUrl,
                       gson: Gson): Retrofit {

        builder.baseUrl(httpUrl)
                .client(client)

        //允许外部对retrofit进行配置
        configuration?.configRetrofit(application, builder)

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

//    fun provideClient(application: Application,){
//
//
//
//    }


    interface RetrofitConfiguration {
        fun configRetrofit(context: Context, builder: Retrofit.Builder)
    }

    interface OkhttpConfiguration {
        fun configOkhttp(context: Context, builder: OkHttpClient.Builder)
    }

    interface RxCacheConfiguration {
        /**
         * 若想自定义 RxCache 的缓存文件夹或者解析方式, 如改成 fastjson
         * 请 `return rxCacheBuilder.persistence(cacheDirectory, new FastJsonSpeaker());`, 否则请 `return null;`
         *
         * @param context
         * @param builder
         * @return [RxCache]
         */
        fun configRxCache(context: Context, builder: RxCache.Builder): RxCache
    }


}