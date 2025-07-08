package com.example.shopersapp.network

import android.content.Context
import android.widget.Toast
import com.example.shopersapp.models.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getItems(shopId: Int, context: Context, callback: (List<Item>) -> Unit) {
    ApiClient.apiService.getItems(shopId).enqueue(object : Callback<List<Item>> {
        override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
            if (response.isSuccessful) {
                callback(response.body() ?: emptyList())
            } else {
                Toast.makeText(context, "Failed to load items", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<List<Item>>, t: Throwable) {
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}
