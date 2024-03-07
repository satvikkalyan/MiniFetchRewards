package com.example.minifetchrewards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.models.Items

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Items>
    lateinit var itemIDs : Array<Int>
    lateinit var itemNames : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemIDs = arrayOf(
            1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,7
        )
        itemNames = arrayOf(
            "1","2","3","4","5","6","1","2","3","4","5","6","1","2","3","4","5","6","1","2","3","4","5","6","1","2","3","4","5","67"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<Items>()
        getData()
    }
    private fun getData(){
        for(i in itemIDs.indices){
            val itemClass = Items(itemIDs[i], itemNames[i])
            dataList.add(itemClass)
        }
        recyclerView.adapter = ItemAdapter(dataList)
    }
}