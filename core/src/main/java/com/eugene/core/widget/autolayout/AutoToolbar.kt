package com.eugene.core.widget.autolayout

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

class AutoToolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private val helper = AutoLayoutHelper(this)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode) {
            helper.adjustChildren()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams =
            AutoToolbar.LayoutParams(context, attrs!!)


    class LayoutParams(val context: Context, attrs: AttributeSet) : Toolbar.LayoutParams(context, attrs), AutoLayoutHelper.AutoLayoutParams {

        private val autoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attrs)

        override fun getAutoLayoutInfo(): AutoLayoutInfo =
                autoLayoutInfo

    }

}