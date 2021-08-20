package com.yesferal.hornsapp.multitype.viewholder

import android.view.View
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.abstraction.Delegate

internal class RowViewHolder<MODEL : Delegate>(
    itemView: View,
    listener: DelegateListener
) : ItemViewHolder<MODEL>(itemView, listener) {

    var itemPosition = 0

    fun onViewRecycled(model: MODEL) {
        model.saveScroll(itemView)
    }
}
