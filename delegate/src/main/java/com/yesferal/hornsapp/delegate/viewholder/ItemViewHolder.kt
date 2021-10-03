package com.yesferal.hornsapp.delegate.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener
import com.yesferal.hornsapp.delegate.abstraction.Delegate

open class ItemViewHolder<DELEGATE : Delegate>(
    itemView: View,
    private val listener: DelegateListener
) : RecyclerView.ViewHolder(itemView) {

    fun onBindViewHolder(delegate: DELEGATE) {
        delegate.onBindViewHolder(itemView, listener)
    }
}
