package com.eugene.core.base.recycler

import android.content.Context
import android.view.ViewGroup

class MultiItemAdapter<T>(context: Context, val multiItemType: IMultiItemType<T>) : BaseAdapter<T>(context, -1) {

    override fun getItemViewType(position: Int): Int {
        return multiItemType.getItemViewType(position, items[position])
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val layoutId = multiItemType.getLayoutId(viewType)
        return BaseHolder.getHolder(context, parent, layoutId)
    }


}