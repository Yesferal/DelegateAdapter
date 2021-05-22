package com.yesferal.hornsapp.model

import android.view.View
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseItem
import kotlinx.android.synthetic.main.item_title.view.*

data class TitleItem(
    val title: String
) : BaseItem<BaseItem.Listener>() {

    override fun bind(view: View, listener: Listener) {
        view.title.text = title
    }

    override val layout: Int
        get() = R.layout.item_title
}
