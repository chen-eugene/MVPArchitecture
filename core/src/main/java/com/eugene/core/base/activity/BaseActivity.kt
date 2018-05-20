package com.eugene.core.base.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.eugene.core.di.component.AppComponent
import com.eugene.core.mvp.BasePresenter
import com.eugene.core.utils.AppUtil
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<*, *>> : AppCompatActivity(), IActivity {

    protected val TAG: String by lazy { this::class.java.name }

    @set:Inject
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutResId = onInitLayout(savedInstanceState)
        if (layoutResId != 0) {
            setContentView(layoutResId)
        }

        onInitData(savedInstanceState)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View {
        return AppUtil.convertAutoView(name!!, context!!, attrs!!) ?: super.onCreateView(name, context, attrs)
    }

    override fun setupComponent(appComponent: AppComponent) {

    }

    override fun useEventBus(): Boolean {
        return true
    }

    override fun useFragment(): Boolean {
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
//        presenter?.onDestroy()
        presenter = null
    }


}