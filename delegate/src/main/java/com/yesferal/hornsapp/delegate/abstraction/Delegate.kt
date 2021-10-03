package com.yesferal.hornsapp.delegate.abstraction

import android.view.View
import com.yesferal.hornsapp.delegate.viewholder.ItemViewHolder

interface Delegate : LayoutBinding {

    fun onBindViewHolder(view: View, listener: DelegateListener)

    fun onViewRecycled(view: View) {}

    fun onCreateViewHolder(
        itemView: View,
        listener: DelegateListener
    ): ItemViewHolder {
        return ItemViewHolder(itemView, listener)
    }
}
