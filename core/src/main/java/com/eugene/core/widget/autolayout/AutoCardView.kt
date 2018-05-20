package com.eugene.core.widget.autolayout

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.zhy.autolayout.utils.AutoLayoutHelper


class AutoCardView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {

    private val helper = AutoLayoutHelper(this)

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams =
            AutoFrameLayout.LayoutParams(context, attrs!!)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode) {
            helper.adjustChildren()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}