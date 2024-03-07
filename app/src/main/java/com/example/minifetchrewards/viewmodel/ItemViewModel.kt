package com.example.minifetchrewards.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.repository.ItemRepository

/**
 * The ItemViewModel provides data to the UI and survives configuration changes such as screen rotations, thereby preventing data loss.
 * @param itemRepository The repository from which this ViewModel will fetch data. It's an abstraction
 *                       layer that manages the data sources, whether it's from network or local database.
 */
class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchItems()
    }

    private fun fetchItems() {
        itemRepository.fetchItems(
            successHandler = { items ->
                _items.value = items
            },
            errorHandler = { throwable ->
                _error.value = throwable.message
            }
        )
    }
}
