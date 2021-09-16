package com.yesferal.hornsapp.model

import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.delegate.InteractiveDelegate

class SecondaryDelegate(
        val title: String,
        val description: String,
        @DrawableRes val image: Int
) : InteractiveDelegate<SecondaryDelegate.Listener> {

    override fun onBindViewDelegate(view: View, listener: Listener) {
        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<TextView>(R.id.description).text = description
        view.findViewById<ImageView>(R.id.image).setImageResource(image)
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_secondary_card

    interface Listener: DelegateListener {
        fun onClick(secondaryDelegate: SecondaryDelegate)
    }
}
