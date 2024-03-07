package com.example.minifetchrewards.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.databinding.ActivityMainBinding
import com.example.minifetchrewards.network.RetrofitClient
import com.example.minifetchrewards.repository.ItemRepository
import com.example.minifetchrewards.viewmodel.ItemViewModel
import com.example.minifetchrewards.viewmodel.ItemViewModelFactory

/**
 * The MainActivity class serves as the entry point for this app's user interface.
 * This activity is responsible for setting up the UI components, initializing the ViewModel with
 * its dependencies, and observing any data changes from the ViewModel to update the UI accordingly.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemRepository = ItemRepository(RetrofitClient.apiService)

    private lateinit var viewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ItemViewModelFactory(itemRepository)
        viewModel = ViewModelProvider(this, factory).get(ItemViewModel::class.java)

        // Set up the RecyclerView with an adapter and layout manager.
        setupRecyclerView()

        // Start observing LiveData objects from the ViewModel to update UI upon data change.
        observeViewModel()
    }

    private fun observeViewModel() {
        // Observe the items LiveData, updating the RecyclerView adapter with new items when data changes.
        viewModel.items.observe(this) { items ->
            (binding.recyclerView.adapter as ItemAdapter).updateItems(items)
        }
        // Observe the error LiveData, displaying a Toast message if an error occurs.
        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Configures the RecyclerView used for displaying the list of items. It sets a LayoutManager
     * and an Adapter.
     */
    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ItemAdapter(arrayListOf())
    }

}