package com.yesferal.hornsapp.multitype.delegate

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.abstraction.Delegate
import com.yesferal.hornsapp.multitype.DelegateAdapter
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener

class MultiTypeContainer private constructor(
    private val items: List<Delegate>,
    private val listener: DelegateListener
) {

    private val adapter: DelegateAdapter by lazy {
        DelegateAdapter.Builder()
            .addItems(items)
            .setListener(listener)
            .build()
    }

    fun attachRecycler(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    }

    fun updateItems(newItems: List<Delegate>) {
        adapter.updateItems(newItems)
    }

    class Builder {
        private var items: MutableList<Delegate> = mutableListOf()

        private var listener: DelegateListener = object : DelegateListener {}

        fun addItem(item: Delegate) = apply {
            this.items.add(item)
        }

        fun addItems(items: List<Delegate>) = apply {
            this.items.addAll(items)
        }

        fun setListener(listener: DelegateListener?) = apply {
            listener?.let { this.listener = listener }
        }

        fun build() = MultiTypeContainer(items, listener)
    }
}
