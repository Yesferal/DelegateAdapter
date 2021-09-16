package com.yesferal.hornsapp.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.delegate.InteractiveDelegate

class CarouselDelegate(
    val title: String,
    @DrawableRes val image: Int
) : InteractiveDelegate<CarouselDelegate.Listener> {
    override fun onBindViewDelegate(view: View, listener: Listener) {
        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<ImageView>(R.id.image).setImageResource(image)
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_carousel_card

    interface Listener: DelegateListener {
        fun onClick(carouselDelegate: CarouselDelegate)
    }
}
