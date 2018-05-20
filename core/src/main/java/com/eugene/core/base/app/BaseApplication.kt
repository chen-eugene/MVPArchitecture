package com.eugene.core.base.app

import android.app.Application
import android.content.Context
import com.eugene.core.di.component.AppComponent
import com.eugene.core.utils.Preconditions

class BaseApplication : Application(), IApp {


    private var appDelegate: IAppLifecycle? = null

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        if (appDelegate == null) {
            appDelegate = AppDelegate(base)
        }
        appDelegate?.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        appDelegate?.onCreate(this)
    }


    override fun onTerminate() {
        super.onTerminate()
        appDelegate?.onTerminate(this)
    }

    override fun getAppComponent(): AppComponent? {
        Preconditions.checkNotNull(appDelegate, "%s cannot be null", AppDelegate::class.java.name)
        Preconditions.checkNotNull(appDelegate is IApp, "%s must be implements %s",
                AppDelegate::class.java.name, IApp::class.java.name)
        return (appDelegate as IApp).getAppComponent()
    }

}