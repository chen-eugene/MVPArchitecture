package com.eugene.core.base.recycler

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent

abstract class OnRecyclerItemClickListener(val recycler: RecyclerView) : RecyclerView.OnItemTouchListener {

    private val gestureDetector: GestureDetectorCompat

    init {
        gestureDetector = GestureDetectorCompat(recycler.context, OnItemGestureListener())
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        gestureDetector.onTouchEvent(e)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(e)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private inner class OnItemGestureListener : GestureDetector.SimpleOnGestureListener() {

        /** 一次单独的轻触抬起手指操作，就是普通的点击事件 */
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            val child = recycler.findChildViewUnder(e?.x!!, e.y)
            if (child != null) {
                val viewHolder = recycler.getChildViewHolder(child)
                onItemClick(viewHolder)
            }
            return true
        }

        /** 长按触发 */
        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
            val child = recycler.findChildViewUnder(e?.x!!, e.y)
            if (child != null) {
                val viewHolder = recycler.getChildViewHolder(child)
                onItemLongClick(viewHolder)
            }
        }

    }

    abstract fun onItemClick(viewHolder: RecyclerView.ViewHolder)

    abstract fun onItemLongClick(viewHolder: RecyclerView.ViewHolder)

}