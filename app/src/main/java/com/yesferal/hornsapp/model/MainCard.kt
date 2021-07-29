package com.yesferal.hornsapp.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseItem

class MainCard(
    val title: String,
    @DrawableRes val image: Int
) : BaseItem<MainCard.Listener>() {

    override fun bind(view: View, listener: Listener) {
        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<ImageView>(R.id.image).setImageResource(image)
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_main_card

    interface Listener: BaseItem.Listener {
        fun onClick(mainCard: MainCard)
    }
}
