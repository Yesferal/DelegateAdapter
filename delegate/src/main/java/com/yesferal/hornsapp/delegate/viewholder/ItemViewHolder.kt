package com.yesferal.hornsapp.delegate.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener
import com.yesferal.hornsapp.delegate.abstraction.Delegate

class ItemViewHolder(
    itemView: View,
    private val listener: DelegateListener
) : RecyclerView.ViewHolder(itemView) {

    var itemPosition = 0

    fun onViewRecycled(delegate: Delegate) {
        delegate.onViewRecycled(itemView)
    }

    fun onBindViewHolder(delegate: Delegate) {
        delegate.onBindViewHolder(itemView, listener)
    }
}
