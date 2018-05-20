package com.eugene.core.widget.autolayout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

class AutoRelativeLayout(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val mHelper = AutoLayoutHelper(this)

    constructor(context: Context) : this(context, null)

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams =
            LayoutParams(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode)
            mHelper.adjustChildren()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    class LayoutParams(val context: Context, attrs: AttributeSet) : RelativeLayout.LayoutParams(context, attrs), AutoLayoutHelper.AutoLayoutParams {

        private val autoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attrs)

        override fun getAutoLayoutInfo(): AutoLayoutInfo =
                autoLayoutInfo

    }
}