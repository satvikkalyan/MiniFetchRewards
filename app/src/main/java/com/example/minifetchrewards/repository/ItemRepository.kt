package com.example.minifetchrewards.repository

import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.models.ListContent
import com.example.minifetchrewards.network.ApiService

class ItemRepository(private val apiService: ApiService) {

    /**
     * Fetches items using the ApiService and processes the result.
     * Upon successful fetching, the items are filtered, sorted, and returned via the successHandler.
     * If an error occurs, the errorHandler is invoked with the error details.
     *
     * @param successHandler A lambda that is called with the list of processed items on success.
     * @param errorHandler A lambda that is called with an error on failure.
     */
    fun fetchItems(successHandler: (ArrayList<ListContent>) -> Unit, errorHandler: (Throwable) -> Unit) {
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

    /**
     * Processes the raw list of items by filtering out any items with null or blank names,
     * and sorting them first by their listId and then by name.
     *
     * @param items The raw list of items fetched from the API.
     * @return A list of processed items that are filtered and sorted.
     */
    fun processItems(items: List<Item>?): ArrayList<ListContent> {
        val groupedItems = items
            ?.filter { it.name?.isNotBlank() == true }
            ?.sortedWith(compareBy({ it.listId }, { it.name }))
            ?.groupBy { it.listId }
            ?.flatMap { entry ->
                listOf(ListContent.Header(entry.key)) + entry.value.map { ListContent.Item(it.id, it.listId, it.name) }
            } ?: arrayListOf()

        return ArrayList(groupedItems)
    }
}
