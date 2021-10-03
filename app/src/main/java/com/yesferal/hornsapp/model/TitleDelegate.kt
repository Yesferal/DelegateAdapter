package com.yesferal.hornsapp.model

import android.view.View
import android.widget.TextView
import com.yesferal.hornsapp.R
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener
import com.yesferal.hornsapp.delegate.delegate.NonInteractiveDelegate

data class TitleDelegate(
    val title: String
) : NonInteractiveDelegate {

    override fun onBindViewDelegate(view: View, listener: DelegateListener) {
        view.findViewById<TextView>(R.id.title).text = title
    }

    override val layout: Int
        get() = R.layout.item_title
}
