/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eugene.core.base.activity

import android.app.Activity
import android.os.Bundle

import org.simple.eventbus.EventBus

/**
 * ================================================
 * [IActivityDelegate] 默认实现类
 *
 *
 * Created by JessYan on 26/04/2017 20:23
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class ActivityDelegateImpl(private var mActivity: Activity?) : IActivityDelegate {
    private var iActivity: IActivity? = null


    init {
        this.iActivity = mActivity as IActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //如果要使用 EventBus 请将此方法返回 true
        if (iActivity!!.useEventBus()) {
            //注册到事件主线
            EventBus.getDefault().register(mActivity)
        }

        //这里提供 AppComponent 对象给 BaseActivity 的子类, 用于 Dagger2 的依赖注入
        //        iActivity.setupComponent(ArmsUtils.obtainAppComponentFromContext(mActivity));
    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onDestroy() {
        //如果要使用 EventBus 请将此方法返回 true
        if (iActivity != null && iActivity!!.useEventBus())
            EventBus.getDefault().unregister(mActivity)
        this.iActivity = null
        this.mActivity = null
    }
}
