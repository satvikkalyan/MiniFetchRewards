package com.example.minifetchrewards

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.databinding.ActivityMainBinding
import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.network.RetrofitClient
import com.example.minifetchrewards.repository.ItemRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemRepository = ItemRepository(RetrofitClient.apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start fetching items to be displayed
        fetchItems()
    }
    /**
     * fetches the list of items from the repository and handles the results.
     */
    private fun fetchItems() {
        itemRepository.fetchItems(
            successHandler = { items ->
                // On success, set up the RecyclerView with the fetched items
                setupRecyclerView(items)
            },
            errorHandler = { throwable ->
                // On error, display a toast message to the user
                Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
            }
        )
    }
    private fun setupRecyclerView(items: ArrayList<Item>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ItemAdapter(items)
    }
}