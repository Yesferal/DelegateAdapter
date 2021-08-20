package com.yesferal.hornsapp.multitype.abstraction

import android.view.View
import com.yesferal.hornsapp.multitype.viewholder.ItemViewHolder

interface Delegate : LayoutBinding {

    fun bindViewHolder(view: View, listener: DelegateListener)

    fun saveScroll(view: View) {}

    fun onCreateViewHolder(
        itemView: View,
        listener: DelegateListener
    ): ItemViewHolder<Delegate> {
        return ItemViewHolder(itemView, listener)
    }
}
