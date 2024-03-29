package com.yesferal.hornsapp.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener
import com.yesferal.hornsapp.delegate.abstraction.Delegate
import com.yesferal.hornsapp.delegate.viewholder.DelegateViewHolder
import java.lang.Exception

class DelegateAdapter private constructor(
    private val delegates: MutableList<Delegate>,
    private val listener: DelegateListener,
    private val viewTypes: HashMap<Int, (
        itemView: View,
        listener: DelegateListener
    ) -> DelegateViewHolder> = hashMapOf()
) : RecyclerView.Adapter<DelegateViewHolder>() {

    init {
        delegates.forEach { updateViewTypes(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DelegateViewHolder {
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
        return delegates[position].layout
    }

    override fun onBindViewHolder(
        holder: DelegateViewHolder,
        position: Int
    ) {
        holder.itemPosition = position
        holder.onBindViewHolder(delegates[position])
    }

    override fun onViewRecycled(holder: DelegateViewHolder) {
        super.onViewRecycled(holder)
        val itemPosition = holder.itemPosition
        if (itemPosition < delegates.size) {
            val item = delegates[itemPosition]
            holder.onViewRecycled(item)
        }
    }

    override fun getItemCount(): Int = delegates.size

    fun updateDelegates(newDelegates: List<Delegate>) {
        delegates.clear()
        viewTypes.clear()
        newDelegates.forEach {
            delegates.add(it)
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
