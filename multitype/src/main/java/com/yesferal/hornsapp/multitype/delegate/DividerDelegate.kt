package com.yesferal.hornsapp.multitype.delegate

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.yesferal.hornsapp.multitype.R
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.util.convertDpToPixel

data class DividerDelegate(
    private val width: Int? = null,
    private val height: Int? = null,
    @ColorRes private val background: Int? = null
) : NonInteractiveDelegate {

    override fun onBindViewDelegate(view: View, listener: DelegateListener) {
        view.findViewById<View>(R.id.view).let { dividerView ->
            val widthAsPixels = width?.let {
                convertDpToPixel(it.toFloat(), view.context)
            }?: view.context.resources.displayMetrics.widthPixels
            val heightAsPixels = height?.let {
                convertDpToPixel(it.toFloat(), view.context)
            }?: view.context.resources.displayMetrics.heightPixels
            background?.let {
                dividerView.setBackgroundColor(ContextCompat.getColor(view.context, it))
            }
            dividerView.layoutParams = FrameLayout.LayoutParams(widthAsPixels, heightAsPixels)
        }
    }

    override val layout: Int
        get() = R.layout.item_divider
}
