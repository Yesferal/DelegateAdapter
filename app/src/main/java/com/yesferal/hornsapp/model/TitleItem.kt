package com.yesferal.hornsapp.model

import android.view.View
import android.widget.TextView
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseItem

data class TitleItem(
    val title: String
) : BaseItem<BaseItem.Listener>() {

    override fun bind(view: View, listener: Listener) {
        view.findViewById<TextView>(R.id.title).text = title
    }

    override val layout: Int
        get() = R.layout.item_title
}
