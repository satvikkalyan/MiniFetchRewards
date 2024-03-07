package com.example.minifetchrewards

import com.example.minifetchrewards.models.Item
import com.example.minifetchrewards.models.ListContent
import com.example.minifetchrewards.network.ApiService
import com.example.minifetchrewards.repository.ItemRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ItemRepositoryTest {

    @Mock
    private lateinit var mockApiService: ApiService

    @Mock
    private lateinit var mockCall: Call<List<Item>>

    @Captor
    private lateinit var callbackCaptor: ArgumentCaptor<Callback<List<Item>>>

    private lateinit var itemRepository: ItemRepository

    @Before
    fun setUp() {
        `when`(mockApiService.fetchItems()).thenReturn(mockCall)
        itemRepository = ItemRepository(mockApiService)
    }

    @Test
    fun fetchItems_Success_ReturnsFilteredSortedItems() {
        val unsortedAndInvalidItems = listOf(
            Item(1, 2, ""),
            Item(2, 1, "Banana"),
            Item(3, 1, "Apple"),
            Item(4, 2, null),
            Item(5, 2, "Carrot")
        )

        itemRepository.fetchItems(successHandler = { items ->
            val expectedItems = arrayListOf<ListContent>(
                ListContent.Header(1),
                ListContent.Item(3, 1, "Apple"),
                ListContent.Item(2, 1, "Banana"),
                ListContent.Header(2),
                ListContent.Item(5, 2, "Carrot")
            )
            assertEquals(expectedItems, items)
        }, errorHandler = {
            fail("Expected success handler to be invoked")
        })

        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onResponse(mockCall, Response.success(unsortedAndInvalidItems))
    }
    @Test
    fun fetchItems_NonSuccessResponse_InvokesErrorHandlerWithCustomError() {
        val errorResponse: Response<List<Item>> = Response.error(400, okhttp3.ResponseBody.create(null, ""))
        itemRepository.fetchItems(successHandler = {
            fail("Expected error handler to be invoked")
        }, errorHandler = { error ->
            assertNotNull(error)
            assertEquals("An error occurred", error.message)
        })
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onResponse(mockCall, errorResponse)
    }
    @Test
    fun fetchItems_Failure_InvokesErrorHandler() {
        val error = Throwable("Network error")
        itemRepository.fetchItems(successHandler = {
            fail("Expected error handler to be invoked")
        }, errorHandler = {
            assertEquals(error.message, it.message)
        })
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onFailure(mockCall, error)
    }}
