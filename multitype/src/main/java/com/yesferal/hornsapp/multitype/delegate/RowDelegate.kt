package com.yesferal.hornsapp.multitype.delegate

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.R
import com.yesferal.hornsapp.multitype.abstraction.Delegate
import com.yesferal.hornsapp.multitype.viewholder.ItemViewHolder
import com.yesferal.hornsapp.multitype.DelegateAdapter
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.viewholder.RowViewHolder

class RowDelegate private constructor(
    private val items: List<Delegate>,
    private val horizontalMargin: Int
) : Delegate {

    private var scrollOffset: Int = 0
    private var scrollPosition: Int = 0

    override fun bindViewHolder(view: View, listener: DelegateListener) {
        bind(view, listener)
    }

    override fun onCreateViewHolder(
        itemView: View,
        listener: DelegateListener
    ): ItemViewHolder<Delegate> {
        return RowViewHolder(itemView, listener)
    }

    override val layout: Int
        get() = R.layout.item_row_multi_type

    override fun saveScroll(view: View) {
        view.findViewById<RecyclerView>(R.id.recyclerView).let { recyclerView ->
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
            scrollPosition = linearLayoutManager?.findFirstVisibleItemPosition() ?: 0
            val firstItemView = linearLayoutManager?.findViewByPosition(scrollPosition)
            firstItemView?.let {
                val layoutParams = it.layoutParams as ViewGroup.MarginLayoutParams?
                scrollOffset = it.left - it.paddingLeft - (layoutParams?.leftMargin ?: 0)
            }
        }
    }

    fun bind(view: View, listener: DelegateListener) {
        val adapter = DelegateAdapter.Builder()
            .addItem(DividerDelegate(horizontalMargin, 0))
            .addItems(items)
            .addItem(DividerDelegate(horizontalMargin, 0))
            .setListener(listener)
            .build()
        val linearLayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        linearLayoutManager.scrollToPositionWithOffset(scrollPosition, scrollOffset)
        view.findViewById<RecyclerView>(R.id.recyclerView).let { recyclerView ->
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
        }
    }

    class Builder {
        private var items: MutableList<Delegate> = mutableListOf()
        private var horizontalMargin: Int = 0

        fun addItems(items: List<Delegate>) = apply {
            this.items.addAll(items)
        }

        fun addHorizontalMargin(horizontalMargin: Int) = apply {
            this.horizontalMargin = horizontalMargin
        }

        fun build() = RowDelegate(items, horizontalMargin)
    }
}
