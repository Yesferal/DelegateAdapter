package com.yesferal.hornsapp.model

import android.view.View
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseItem
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding
import kotlinx.android.synthetic.main.item_main_card.view.*

class MainCard(
    val title: String,
    @DrawableRes val image: Int
) : BaseItem<MainCard.Listener>() {

    override fun bind(view: View, listener: Listener) {
        view.title.text = title
        view.image.setImageResource(image)
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_main_card

    interface Listener: ViewHolderBinding.Listener {
        fun onClick(mainCard: MainCard)
    }
}
