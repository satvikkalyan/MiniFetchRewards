package com.example.minifetchrewards.models


/**
 * Data class representing an item fetched from the API.
 * This class holds the data for each item, which includes an id, listId, and name.
 *
 * @param id The unique identifier for the item.
 * @param listId The identifier for the list to which this item belongs.
 * @param name The name of the item. It's nullable since the API might return items with no name.
 */
data class Item(
    var id: Int,
    var listId: Int,
    var name: String?
)