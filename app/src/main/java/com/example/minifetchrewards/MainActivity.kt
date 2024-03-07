package com.example.minifetchrewards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.databinding.ActivityMainBinding
import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val baseUrl = "https://fetch-hiring.s3.amazonaws.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchItems()
    }

    private fun fetchItems() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.fetchItems().enqueue(object : retrofit2.Callback<List<Item>> {
            override fun onResponse(call: retrofit2.Call<List<Item>>, response: retrofit2.Response<List<Item>>) {
                if (response.isSuccessful) {
                    val filteredSortedItems = response.body()
                        ?.filter { it.name?.isNotBlank() == true }
                        ?.sortedWith(compareBy({ it.listId }, { it.name }))
                        ?.let { ArrayList(it) }
                    filteredSortedItems?.let {
                        setupRecyclerView(it)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Item>>, t: Throwable) {
                // Handle failure
            }
        })
    }
    private fun setupRecyclerView(items: ArrayList<Item>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ItemAdapter(items)
    }
}