package com.yesferal.hornsapp.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.abstraction.ViewHolderBinding
import java.lang.Exception

class MultiTypeAdapter private constructor(
    private val items: MutableList<ViewHolderBinding>,
    private val listener: BaseItem.Listener,
    private val viewTypes: HashMap<Int, (
            itemView: View,
            listener: BaseItem.Listener
    ) -> BaseViewHolder<ViewHolderBinding>> = hashMapOf()
) : RecyclerView.Adapter<BaseViewHolder<ViewHolderBinding>>() {

    init {
        items.forEach { updateViewTypes(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewHolderBinding> {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return viewTypes[viewType]?.invoke(itemView, listener)
            ?: throw Exception("${this::class.java} could not found viewType #$viewType " +
                    "or layout ${parent.context.resources.getResourceName(viewType)}")
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layout
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewHolderBinding>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<BaseItem<out BaseItem.Listener>>) {
        items.clear()
        newItems.forEach {
            items.add(it)
            updateViewTypes(it)
        }
        notifyDataSetChanged()
    }

    private fun updateViewTypes(newItem: ViewHolderBinding) {
        if (!viewTypes.containsKey(newItem.layout)) {
            viewTypes[newItem.layout] = { view, listener ->
                newItem.onCreateViewHolder(view, listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class Builder {
        private var items: MutableList<ViewHolderBinding> = mutableListOf()

        private var listener: BaseItem.Listener = object : BaseItem.Listener {}

        fun addItem(item: BaseItem<out BaseItem.Listener>) = apply {
            this.items.add(item)
        }

        fun addItems(items: List<BaseItem<out BaseItem.Listener>>) = apply {
            this.items.addAll(items)
        }

        fun setListener(listener: BaseItem.Listener) = apply {
            this.listener = listener
        }

        fun build() = MultiTypeAdapter(items, listener)
    }
}
