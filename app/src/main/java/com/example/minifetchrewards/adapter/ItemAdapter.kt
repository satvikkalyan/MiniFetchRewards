package com.example.minifetchrewards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minifetchrewards.databinding.ItemLayoutBinding
import com.example.minifetchrewards.models.Items

class ItemAdapter(private val itemList: ArrayList<Items>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.item = currentItem
        holder.binding.executePendingBindings()
    }
    override fun getItemCount(): Int {
            return itemList.size
    }
}