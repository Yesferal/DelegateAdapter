package com.yesferal.hornsapp.multitype.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.abstraction.Delegate

open class ItemViewHolder<MODEL : Delegate>(
    itemView: View,
    private val listener: DelegateListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: MODEL) {
        model.bindViewHolder(itemView, listener)
    }
}
