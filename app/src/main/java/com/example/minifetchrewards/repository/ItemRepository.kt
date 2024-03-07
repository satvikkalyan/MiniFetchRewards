package com.example.minifetchrewards.repository

import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.network.ApiService

class ItemRepository(private val apiService: ApiService) {

    fun fetchItems(successHandler: (ArrayList<Item>) -> Unit, errorHandler: (Throwable) -> Unit) {
        apiService.fetchItems().enqueue(object : retrofit2.Callback<List<Item>> {
            override fun onResponse(call: retrofit2.Call<List<Item>>, response: retrofit2.Response<List<Item>>) {
                if (response.isSuccessful) {
                    val filteredSortedItems = processItems(response.body())
                    successHandler(filteredSortedItems)
                } else {
                    errorHandler(Throwable("An error occurred"))
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Item>>, t: Throwable) {
                errorHandler(t)
            }
        })
    }
    fun processItems(items: List<Item>?): ArrayList<Item> {
        return items
            ?.filter { it.name?.isNotBlank() == true }
            ?.sortedWith(compareBy({ it.listId }, { it.name }))
            ?.let { ArrayList(it) } ?: arrayListOf()
    }
}
