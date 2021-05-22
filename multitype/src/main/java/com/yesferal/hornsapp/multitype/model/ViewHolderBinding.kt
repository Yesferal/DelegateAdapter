package com.yesferal.hornsapp.multitype.model

import android.view.View
import com.yesferal.hornsapp.multitype.BaseViewHolder

abstract class ViewHolderBinding: LayoutBinding {

    interface Listener

    internal abstract fun bindViewHolder(view: View, listener: Listener)

    fun onCreateViewHolder(
        itemView: View,
        listener: Listener
    ) : BaseViewHolder<ViewHolderBinding> {
        return BaseViewHolder(itemView, listener)
    }
}
