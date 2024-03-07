package com.example.minifetchrewards

import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.models.ListContent
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemAdapterTest {

    private lateinit var adapter: ItemAdapter
    private lateinit var listContents: ArrayList<ListContent>

    @Before
    fun setUp() {
        listContents = ArrayList<ListContent>().apply {
            add(ListContent.Header(1))
            add(ListContent.Item(1, 1, "Item 1"))
            add(ListContent.Item(2, 1, "Item 2"))
        }

        adapter = ItemAdapter(listContents)
    }

    @Test
    fun `item count should match including headers`() {
        // Expect 3 because we have 1 header and 2 items
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun `verify item at position`() {
        // Testing against the second item in the list, which is the first item after the header
        val expectedItem = ListContent.Item(1, 1, "Item 1")
        val actualItem = listContents[1] // This gets the first ListContent.Item
        assertEquals(expectedItem, actualItem)
    }

    @Test
    fun `verify header at position`() {
        // Verifying the header
        val expectedHeader = ListContent.Header(1)
        val actualHeader = listContents[0] // This is the header
        assertEquals(expectedHeader, actualHeader)
    }

    @Test
    fun `verify getItemCount includes headers and items`() {
        // This should include both headers and items
        assertEquals(3, adapter.itemCount)
    }
}
