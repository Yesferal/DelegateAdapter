package com.yesferal.hornsapp.model

import android.view.View
import android.widget.TextView
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.delegate.NonInteractiveViewDelegate

data class TitleDelegate(
    val title: String
) : NonInteractiveViewDelegate() {

    override fun onBindViewDelegate(view: View, listener: DelegateListener) {
        view.findViewById<TextView>(R.id.title).text = title
    }

    override val layout: Int
        get() = R.layout.item_title
}
