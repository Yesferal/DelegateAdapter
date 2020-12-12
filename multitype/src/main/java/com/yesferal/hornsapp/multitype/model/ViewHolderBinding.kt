package com.yesferal.hornsapp.multitype.model

import android.view.View
import com.yesferal.hornsapp.multitype.BaseViewHolder

interface ViewHolderBinding: LayoutBinding {

    interface Listener

    fun onCreateViewHolder(
        itemView: View,
        listener: Listener
    ) : BaseViewHolder<out ViewHolderBinding>
}