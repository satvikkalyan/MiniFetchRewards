package com.example.minifetchrewards.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minifetchrewards.R
import com.example.minifetchrewards.models.Items

class ItemAdapter(private val itemList: ArrayList<Items>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvItemId : TextView = itemView.findViewById(R.id.itemId)
        val rvItemName : TextView = itemView.findViewById(R.id.itemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.rvItemId.text = currentItem.itemID.toString()
        holder.rvItemName.text = currentItem.itemName
    }

    override fun getItemCount(): Int {
            return itemList.size
    }
}