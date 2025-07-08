package com.example.shopersapp
import com.example.shopersapp.network.ApiClient

import android.content.Context
import android.widget.Toast
import com.example.shopersapp.models.Item
import com.example.shopersapp.models.PlaceOrderRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun placeOrder(shopId: Int, items: List<Item>, context: Context) {
    val request = PlaceOrderRequest(shopId, items)
    ApiClient.apiService.placeOrder(request).enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to place order", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}
