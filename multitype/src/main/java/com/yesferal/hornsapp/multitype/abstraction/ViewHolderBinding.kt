package com.yesferal.hornsapp.multitype.abstraction

import android.view.View
import com.yesferal.hornsapp.multitype.BaseItem
import com.yesferal.hornsapp.multitype.BaseViewHolder

abstract class ViewHolderBinding: LayoutBinding {

    internal abstract fun bindViewHolder(view: View, listener: BaseItem.Listener)

    fun onCreateViewHolder(
        itemView: View,
        listener: BaseItem.Listener
    ) : BaseViewHolder<ViewHolderBinding> {
        return BaseViewHolder(itemView, listener)
    }
}
