package com.example.minifetchrewards

import com.example.minifetchrewards.adapter.ItemAdapter
import com.example.minifetchrewards.models.Item
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
    private lateinit var itemList: ArrayList<Item>



    @Before
    fun setUp() {
        itemList = ArrayList()
        itemList.add(Item(1, 1,"1"))
        itemList.add(Item(2, 2,"2"))
        itemList.add(Item(3, 3,"3"))

        adapter = ItemAdapter(itemList)
    }

    @Test
    fun `item count should match`() {
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun `verify item at position`() {
        val expectedItem = Item(1, 1,"1")
        assertEquals(expectedItem, itemList[0])
    }


    @Test
    fun `verify getItemCount`() {
        assertEquals(3, adapter.itemCount)
    }

}
