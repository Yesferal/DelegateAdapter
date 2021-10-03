package com.yesferal.hornsapp.delegate.delegate

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.yesferal.hornsapp.delegate.R
import com.yesferal.hornsapp.delegate.util.convertDpToPixel

data class DividerDelegate(
    private val width: Int = 0,
    private val height: Int = 0,
    @ColorRes private val background: Int? = null
) : NonInteractiveDelegate {

    override fun onBindViewDelegate(view: View) {
        view.findViewById<View>(R.id.view).let { dividerView ->
            val widthAsPixels = convertDpToPixel(width.toFloat(), view.context)
            val heightAsPixels = convertDpToPixel(height.toFloat(), view.context)
            dividerView.layoutParams = FrameLayout.LayoutParams(widthAsPixels, heightAsPixels)
            background?.let {
                dividerView.setBackgroundColor(ContextCompat.getColor(view.context, it))
            }
        }
    }

    override val layout: Int
        get() = R.layout.item_divider
}
