package com.yesferal.hornsapp.delegate.delegate

import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.delegate.R
import com.yesferal.hornsapp.delegate.abstraction.Delegate
import com.yesferal.hornsapp.delegate.viewholder.DelegateViewHolder
import com.yesferal.hornsapp.delegate.DelegateAdapter
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener

class RowDelegate private constructor(
    private val items: List<Delegate>,
    private val horizontalMargin: Int,
    @ColorRes private val background: Int?,
    private val elevation: Float
) : InteractiveDelegate<DelegateListener> {

    private var scrollOffset: Int = 0
    private var scrollPosition: Int = 0

    override fun onCreateViewHolder(
        itemView: View,
        listener: DelegateListener
    ): DelegateViewHolder {
        return DelegateViewHolder(itemView, listener)
    }

    override val layout: Int
        get() = R.layout.item_row_delegate

    override fun onViewRecycled(view: View) {
        view.findViewById<RecyclerView>(R.id.recyclerView)?.let { recyclerView ->
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
            scrollPosition = linearLayoutManager?.findFirstVisibleItemPosition() ?: 0
            val firstItemView = linearLayoutManager?.findViewByPosition(scrollPosition)
            firstItemView?.let {
                val layoutParams = it.layoutParams as ViewGroup.MarginLayoutParams?
                scrollOffset = it.left - it.paddingLeft - (layoutParams?.leftMargin ?: 0)
            }
        }
    }

    override fun onBindViewDelegate(view: View, listener: DelegateListener) {
        val adapter = DelegateAdapter.Builder()
            .addItem(DividerDelegate(horizontalMargin, 0))
            .addItems(items)
            .addItem(DividerDelegate(horizontalMargin, 0))
            .setListener(listener)
            .build()
        val linearLayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        linearLayoutManager.scrollToPositionWithOffset(scrollPosition, scrollOffset)
        view.findViewById<RecyclerView>(R.id.recyclerView)?.let { recyclerView ->
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
        }
        background?.let {
            view.setBackgroundColor(ContextCompat.getColor(view.context, it))
        }
        view.elevation = elevation
    }

    class Builder {
        private var items: MutableList<Delegate> = mutableListOf()
        private var horizontalMargin: Int = 0
        @ColorRes private var background: Int? = null
        private var elevation: Float = 0F

        fun addItems(items: List<Delegate>) = apply {
            this.items.addAll(items)
        }

        fun addHorizontalMargin(horizontalMargin: Int) = apply {
            this.horizontalMargin = horizontalMargin
        }

        fun addBackground(background: Int) = apply {
            this.background = background
        }

        fun addElevation(elevation: Float) = apply {
            this.elevation = elevation
        }

        fun build() = RowDelegate(items, horizontalMargin, background, elevation)
    }
}
