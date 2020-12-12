package com.yesferal.hornsapp.model

import android.view.View
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseViewHolder
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding
import kotlinx.android.synthetic.main.item_main_card.view.*

class MainCardModel(
    val title: String,
    @DrawableRes val image: Int
) : ViewHolderBinding {
    override fun onCreateViewHolder(
        itemView: View,
        listener: ViewHolderBinding.Listener
    ) = MainViewHolder(itemView, listener as Listener)

    override val layout: Int
        get() = R.layout.item_main_card

    interface Listener: ViewHolderBinding.Listener {
        fun onClick(mainCardModel: MainCardModel)
    }

    class MainViewHolder(
            itemView: View,
            private val listener: Listener
    ) : BaseViewHolder<MainCardModel>(itemView) {
        override fun bind(model: MainCardModel) {
            itemView.title.text = model.title
            itemView.image.setImageResource(model.image)
            itemView.setOnClickListener {
                listener.onClick(model)
            }
        }
    }
}