package com.eugene.core.widget.autolayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

class AutoLinearLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context) {

    private val helper = AutoLayoutHelper(this)

    constructor(context: Context) : this(context, null)

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams =
            LayoutParams(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode) {
            helper.adjustChildren()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    class LayoutParams(context: Context, attrs: AttributeSet) : LinearLayout.LayoutParams(context, attrs), AutoLayoutHelper.AutoLayoutParams {

        private val autoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attrs)

        override fun getAutoLayoutInfo(): AutoLayoutInfo =
                autoLayoutInfo

    }

}