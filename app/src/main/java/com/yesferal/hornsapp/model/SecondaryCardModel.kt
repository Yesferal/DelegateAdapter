package com.yesferal.hornsapp.model

import android.view.View
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseViewHolder
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding
import kotlinx.android.synthetic.main.item_secondary_card.view.*

class SecondaryCardModel(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
) : ViewHolderBinding {
    override fun onCreateViewHolder(
        itemView: View,
        listener: ViewHolderBinding.Listener
    ) = SecondaryViewHolder(itemView, listener as Listener)

    override val layout: Int
        get() = R.layout.item_secondary_card

    interface Listener: ViewHolderBinding.Listener {
        fun onClick(secondaryCardModel: SecondaryCardModel)
    }

    class SecondaryViewHolder(
            itemView: View,
            private val listener: Listener
    ) : BaseViewHolder<SecondaryCardModel>(itemView) {
        override fun bind(model: SecondaryCardModel) {
            itemView.title.text = model.title
            itemView.description.text = model.description
            itemView.image.setImageResource(model.image)
            itemView.setOnClickListener {
                listener.onClick(model)
            }
        }
    }
}