package com.eugene.core.base.app

import com.eugene.core.di.component.AppComponent

interface IApp {

    fun getAppComponent(): AppComponent?

}