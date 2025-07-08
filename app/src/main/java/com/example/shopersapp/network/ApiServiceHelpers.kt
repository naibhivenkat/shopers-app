package com.example.shopersapp.network

import android.content.Context
import com.example.shopersapp.models.Item
import retrofit2.Call

fun fetchItems(shopId: Int, context: Context, callback: (List<Item>) -> Unit) {
    val call = ApiClient.apiService.getItems(shopId)
    call.enqueue(object : retrofit2.Callback<List<Item>> {
        override fun onResponse(
            call: Call<List<Item>>,
            response: retrofit2.Response<List<Item>>
        ) {
            if (response.isSuccessful) {
                callback(response.body() ?: emptyList())
            } else {
                callback(emptyList())
            }
        }

        override fun onFailure(call: Call<List<Item>>, t: Throwable) {
            callback(emptyList())
        }
    })
}
