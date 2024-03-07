package com.example.minifetchrewards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minifetchrewards.databinding.HeaderLayoutBinding
import com.example.minifetchrewards.databinding.ItemLayoutBinding
import com.example.minifetchrewards.models.ListContent

/**
 * ItemAdapter is an Adapter for the RecyclerView to display items.
 *
 * @param itemList List of Item objects to be displayed in the RecyclerView.
 */
class ItemAdapter(private val itemList: ArrayList<ListContent>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    class ItemViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ListContent.Item){
            binding.item = item
        }
    }
    class HeaderViewHolder(private val headerLayoutBinding: HeaderLayoutBinding) : RecyclerView.ViewHolder(headerLayoutBinding.root){
        fun bind(header: ListContent.Header){
            headerLayoutBinding.header = header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Create the appropriate ViewHolder based on the viewType.
        return when(viewType){
                TYPE_ITEM -> ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
                TYPE_HEADER -> HeaderViewHolder(HeaderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
                else -> throw IllegalArgumentException("Invalid View")
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Bind the appropriate content to the ViewHolder based on its instance type.
        return when(holder){
            is ItemViewHolder -> holder.bind(itemList[position] as ListContent.Item)
            is HeaderViewHolder -> holder.bind(itemList[position] as ListContent.Header)
            else -> {}
        }
    }
    override fun getItemCount(): Int {
            return itemList.size
    }

    /**
     * Update the adapter's item list and refresh the RecyclerView.
     *
     * @param newItems The new list of ListContent to replace the old list.
     */
    fun updateItems(newItems: List<ListContent>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        // Determine the type of view at the given position.
        return when (itemList[position]) {
            is ListContent.Header -> TYPE_HEADER
            is ListContent.Item -> TYPE_ITEM
        }
    }

}