package com.example.minifetchrewards.network

import com.example.minifetchrewards.models.Item
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun fetchItems(): Call<List<Item>>
}
