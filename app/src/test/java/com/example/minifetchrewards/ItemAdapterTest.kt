package com.example.minifetchrewards

import android.view.ViewGroup
import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.models.Items
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemAdapterTest {

    private lateinit var adapter: ItemAdapter

    @Mock
    private lateinit var itemList: ArrayList<Items>

    @Mock
    private lateinit var mockParent: ViewGroup


    @Before
    fun setUp() {
        itemList = ArrayList()
        itemList.add(Items(1, "Item 1"))
        itemList.add(Items(2, "Item 2"))
        itemList.add(Items(3, "Item 3"))

        adapter = ItemAdapter(itemList)
    }

    @Test
    fun `item count should match`() {
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun `verify item at position`() {
        val expectedItem = Items(1, "Item 1")
        assertEquals(expectedItem, itemList[0])
    }


    @Test
    fun `verify getItemCount`() {
        assertEquals(3, adapter.itemCount)
    }

}
