package com.yesferal.hornsapp.multitype

import android.view.View
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding

abstract class BaseItem<LISTENER: ViewHolderBinding.Listener>: ViewHolderBinding() {

    abstract fun bind(view: View, listener: LISTENER)

    override fun bindViewHolder(view: View, listener: Listener) {
        @Suppress("UNCHECKED_CAST")
        bind(view, listener as LISTENER)
    }
}
