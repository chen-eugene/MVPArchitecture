package com.eugene.core.base.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BaseHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val views = SparseArray<View>()

    companion object {
        fun getHolder(context: Context, container: ViewGroup, layout: Int): BaseHolder {
            val itemView = LayoutInflater.from(context).inflate(layout, container, false)
            return BaseHolder(itemView)
        }
    }

    fun <V : View> findView(id: Int): V {
        var view = views[id]
        if (view == null) {
            view = itemView.findViewById(id)
            views[id, view]
        }
        return view as V
    }

    fun setOnClickListenr(id: Int, listener: View.OnClickListener) {

        val view = findView<View>(id)

    }


}