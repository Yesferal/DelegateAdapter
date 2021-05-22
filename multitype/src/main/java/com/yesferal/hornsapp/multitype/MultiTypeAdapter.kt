package com.yesferal.hornsapp.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.multitype.model.ViewHolderBinding
import java.lang.Exception

class MultiTypeAdapter (
    private val listener: ViewHolderBinding.Listener = object : ViewHolderBinding.Listener {},
    private val items: MutableList<ViewHolderBinding> = mutableListOf()
) : RecyclerView.Adapter<BaseViewHolder<ViewHolderBinding>>() {

    private val viewTypes: HashMap<Int, (
        itemView: View,
        listener: ViewHolderBinding.Listener
    ) -> BaseViewHolder<ViewHolderBinding>> = hashMapOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewHolderBinding> {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return viewTypes[viewType]?.invoke(itemView, listener)
            ?: throw Exception("${this::class.java} could not found viewType or layout #$viewType")
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

    fun setItems(newItems: List<BaseItem<out ViewHolderBinding.Listener>>) {
        items.clear()
        newItems.forEach {
            items.add(it)
            viewTypes[it.layout] = { view, listener ->
                it.onCreateViewHolder(view, listener)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}
