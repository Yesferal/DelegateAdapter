package com.yesferal.hornsapp.multitype

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding

class BaseViewHolder<MODEL: ViewHolderBinding> (
    itemView: View,
    private val listener: ViewHolderBinding.Listener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: MODEL) {
        model.bindViewHolder(itemView, listener)
    }
}
