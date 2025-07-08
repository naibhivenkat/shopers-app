package com.example.shopersapp.network

import android.content.Context
import android.widget.Toast
import com.example.shopersapp.models.Item
import com.example.shopersapp.models.PlaceOrderRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun placeOrder(shopId: Int, cartItems: List<Item>, context: Context) {
    val request = PlaceOrderRequest(shopId, cartItems)
    ApiClient.apiService.placeOrder(request).enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            Toast.makeText(context, "Order placed successfully!", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Toast.makeText(context, "Order failed: ${t.message}", Toast.LENGTH_LONG).show()
        }
    })
}
