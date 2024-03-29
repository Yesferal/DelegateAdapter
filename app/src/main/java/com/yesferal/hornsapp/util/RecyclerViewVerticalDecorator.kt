package com.yesferal.hornsapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewVerticalDecorator (
    private val padding: Int = 8
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.context == null) { return }

        val itemPosition = parent.getChildAdapterPosition(view)
        val density = parent.context.resources.displayMetrics.density

        val adapter = parent.adapter
        if (adapter != null && itemPosition == adapter.itemCount - 1) {
            outRect.bottom = (density * (padding)).toInt()
        }
    }
}
