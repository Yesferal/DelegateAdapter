package com.yesferal.hornsapp.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.abstraction.Delegate
import com.yesferal.hornsapp.multitype.viewholder.ItemViewHolder
import com.yesferal.hornsapp.multitype.viewholder.RowViewHolder
import java.lang.Exception

class DelegateAdapter private constructor(
    private val items: MutableList<Delegate>,
    private val listener: DelegateListener,
    private val viewTypes: HashMap<Int, (
        itemView: View,
        listener: DelegateListener
    ) -> ItemViewHolder<Delegate>> = hashMapOf()
) : RecyclerView.Adapter<ItemViewHolder<Delegate>>() {

    init {
        items.forEach { updateViewTypes(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<Delegate> {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return viewTypes[viewType]?.invoke(itemView, listener)
            ?: throw Exception(
                "${this::class.java} could not found viewType #$viewType or layout ${
                    parent.context.resources.getResourceName(
                        viewType
                    )
                }"
            )
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layout
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder<Delegate>,
        position: Int
    ) {
        if (holder is RowViewHolder) {
            holder.itemPosition = position
        }
        holder.onBindViewHolder(items[position])
    }

    fun updateItems(newItems: List<Delegate>) {
        items.clear()
        newItems.forEach {
            items.add(it)
            updateViewTypes(it)
        }
        notifyDataSetChanged()
    }

    private fun updateViewTypes(newItem: Delegate) {
        if (!viewTypes.containsKey(newItem.layout)) {
            viewTypes[newItem.layout] = { view, listener ->
                newItem.onCreateViewHolder(view, listener)
            }
        }
    }

    override fun onViewRecycled(holder: ItemViewHolder<Delegate>) {
        super.onViewRecycled(holder)
        if (holder is RowViewHolder) {
            val item = items[holder.itemPosition]
            holder.onViewRecycled(item)
        }
    }

    override fun getItemCount(): Int = items.size

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

        fun build() = DelegateAdapter(items, listener)
    }
}
