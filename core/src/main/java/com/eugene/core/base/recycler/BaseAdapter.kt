package com.eugene.core.base.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.core.base.recycler.BaseHolder

abstract class BaseAdapter<T>(val context: Context, private val layoutId: Int) : RecyclerView.Adapter<BaseHolder>() {

    var items: MutableList<T> = mutableListOf()

    var onItemClickListener: OnItemClickListener? = null

    constructor(context: Context, layoutId: Int, data: MutableList<T>) : this(context, layoutId) {
        this.items = data
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.itemView?.setOnClickListener {
            onItemClickListener?.onItemClick(it, position)
            onItemClick(it, position)
        }
    }

    fun convert(holder: BaseHolder, t: T) {}

    override fun getItemCount(): Int = items.size

    open fun onItemClick(parent: View, position: Int) {}

    interface OnItemClickListener {

        fun onItemClick(parent: View, position: Int)

    }

    fun insertItem(item: T, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, items.size)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

}