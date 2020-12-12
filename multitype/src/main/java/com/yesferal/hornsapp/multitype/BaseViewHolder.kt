package com.yesferal.hornsapp.multitype

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding

abstract class BaseViewHolder<MODEL: ViewHolderBinding>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(model: MODEL)
}