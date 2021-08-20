package com.yesferal.hornsapp.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.delegate.ViewDelegate

class CarouselCard(
    val title: String,
    @DrawableRes val image: Int
) : ViewDelegate<CarouselCard.Listener>() {
    override fun bind(view: View, listener: Listener) {
        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<ImageView>(R.id.image).setImageResource(image)
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_carousel_card

    interface Listener: DelegateListener {
        fun onClick(carouselCard: CarouselCard)
    }
}
