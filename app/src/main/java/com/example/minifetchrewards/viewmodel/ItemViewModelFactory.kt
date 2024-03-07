package com.example.minifetchrewards.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minifetchrewards.repository.ItemRepository

/**
 * Factory class for creating instances of the ItemViewModel. This factory is used when the ViewModel
 * has constructor parameters, allowing for the injection of dependencies such as repositories.
 *
 *
 * @param repository The ItemRepository instance that will be passed to the ItemViewModel.
 */
class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}