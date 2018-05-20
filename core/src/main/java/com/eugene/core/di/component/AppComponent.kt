package com.eugene.core.di.component

import com.eugene.core.di.module.AppModule
import com.eugene.core.di.module.ClientModule
import com.eugene.core.integration.IRepositoryManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ClientModule::class])
interface AppComponent {

    fun retrofit(): Retrofit

    fun repository(): IRepositoryManager

}