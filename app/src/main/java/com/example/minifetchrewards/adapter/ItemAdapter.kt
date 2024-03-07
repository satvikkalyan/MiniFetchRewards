package com.example.minifetchrewards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minifetchrewards.databinding.ItemLayoutBinding
import com.example.minifetchrewards.models.Item

class ItemAdapter(private val itemList: ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.item = currentItem
    }
    override fun getItemCount(): Int {
            return itemList.size
    }
}