package com.yesferal.hornsapp.multitype.viewholder

import android.view.View
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.abstraction.Delegate

internal class RowViewHolder<DELEGATE : Delegate>(
    itemView: View,
    listener: DelegateListener
) : ItemViewHolder<DELEGATE>(itemView, listener) {

    var itemPosition = 0

    fun onViewRecycled(delegate: DELEGATE) {
        delegate.onViewRecycled(itemView)
    }
}
