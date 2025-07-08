package com.example.shopersapp.network

import android.content.Context
import android.widget.Toast
import com.example.shopersapp.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun fetchItems(shopId: Int, context: Context, onResult: (List<Item>) -> Unit) {
    ApiClient.apiService.getItems(shopId).enqueue(object : Callback<List<Item>> {
        override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
            if (response.isSuccessful) {
                onResult(response.body() ?: emptyList())
            } else {
                Toast.makeText(context, "Failed to load items", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<List<Item>>, t: Throwable) {
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}

fun placeOrder(shopId: Int, items: List<Item>, context: Context) {
    Toast.makeText(context, "Order placed for $shopId items!", Toast.LENGTH_SHORT).show()
    // Add Retrofit API call here if backend supports order placement
}
