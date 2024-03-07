package com.example.minifetchrewards.models
/**
 * Sealed class representing the content to be displayed in a list.
 * This includes both the individual items and the headers for grouping.
 *
 * There are two types of content that extend ListContent:
 * - Item: Represents an individual item with an id, listId, and a nullable name.
 * - Header: Represents a header that categorizes items by listId.
 */

sealed class ListContent {

    /**
     * Represents an individual item in a list with a unique identifier, a list identifier,
     * and a name that can be null if not provided.
     *
     * @param id The unique identifier for this item.
     * @param listId The identifier for the list to which this item belongs.
     * @param name The name of the item, which is nullable.
     */
    data class Item(val id: Int, val listId: Int, val name: String?): ListContent()

    /**
     * Represents a header used for grouping items within the list by listId.
     *
     * @param listId The identifier for the list grouping.
     */
    data class Header(val listId: Int): ListContent()
}
