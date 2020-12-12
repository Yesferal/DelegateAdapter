package com.yesferal.hornsapp.model

import android.view.View
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.BaseViewHolder
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding
import kotlinx.android.synthetic.main.item_title.view.*

data class TitleModel(
    val title: String
) : ViewHolderBinding {
    override fun onCreateViewHolder(
        itemView: View,
        listener: ViewHolderBinding.Listener
    ) = TitleViewHolder(itemView)

    override val layout: Int
        get() = R.layout.item_title

    class TitleViewHolder(
            itemView: View
    ) : BaseViewHolder<TitleModel>(itemView) {
        override fun bind(model: TitleModel) {
            itemView.title.text = model.title
        }
    }
}