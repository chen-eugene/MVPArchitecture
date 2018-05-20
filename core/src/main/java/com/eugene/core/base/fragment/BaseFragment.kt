package com.eugene.core.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.eugene.core.R

abstract class BaseFragment : Fragment() {

    /**
     * 是否初始化了显示内容
     */
    private var isInitContent = false
    /**
     * 存放内容的容器
     */
    private var contentView: ViewGroup? = null


    override fun useEventBus(): Boolean {
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!isLazyFragment()) {
            onInitLazyView(savedInstanceState, contentView)
            return contentView
                    ?: super.onCreateView(inflater, container, savedInstanceState)
        } else {
            return if (userVisibleHint && !isInitContent) {
                isInitContent = true
                onInitLazyView(savedInstanceState, contentView)
                contentView ?: super.onCreateView(inflater, container, savedInstanceState)
            } else {
                contentView = FrameLayout(activity)
                contentView?.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                inflater.inflate(R.layout.fragment_lazy_loading, contentView)
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isInitContent && contentView != null) {
            isInitContent = true
            onInitLazyView(null, contentView)
        }
        if (isInitContent && contentView != null) {
            if (isVisibleToUser) {

            }
        }
    }

    /**
     * 初始化延时视图
     */
    open fun onInitLazyView(savedInstanceState: Bundle?, view: View?) {

        if (isLazyFragment() && contentView?.parent != null) {
            contentView?.removeAllViews()
            LayoutInflater.from(context).inflate(onInitLayout(savedInstanceState), contentView)
        } else {
            isInitContent = true
            val res = onInitLayout(savedInstanceState)
            contentView = LayoutInflater.from(context).inflate(res, null) as ViewGroup?
        }

    }

    fun <V : View> findViewById(id: Int): V? {
        return contentView?.findViewById(id)
    }


}