package com.yesferal.hornsapp.multitype.delegate

import android.view.View
import android.widget.LinearLayout
import com.yesferal.hornsapp.multitype.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.util.convertDpToPixel

class DividerDelegate(
    private val width: Int,
    private val height: Int
) : NonInteractiveViewDelegate() {

    override fun onBindViewDelegate(view: View, listener: DelegateListener) {
        view.findViewById<View>(R.id.view).also {
            val widthAsPixels = convertDpToPixel(width.toFloat(), view.context)
            val heightAsPixels = convertDpToPixel(height.toFloat(), view.context)
            it.layoutParams = LinearLayout.LayoutParams(widthAsPixels, heightAsPixels)
        }
    }

    override val layout: Int
        get() = R.layout.item_divider
}
