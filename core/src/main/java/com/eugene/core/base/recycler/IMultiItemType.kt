package com.eugene.core.base.recycler

interface IMultiItemType<in T> {

    fun getLayoutId(itemType: Int): Int

    fun getItemViewType(position: Int, t: T): Int

}