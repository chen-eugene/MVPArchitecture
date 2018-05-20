package com.eugene.core.widget.autolayout

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.zhy.autolayout.AutoLayoutInfo
import com.zhy.autolayout.utils.AutoLayoutHelper

class AutoConstraintLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
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

    class LayoutParams(context: Context, attrs: AttributeSet) : ConstraintLayout.LayoutParams(context, attrs), AutoLayoutHelper.AutoLayoutParams {

        private val autoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attrs)

        override fun getAutoLayoutInfo(): AutoLayoutInfo =
                autoLayoutInfo

    }

}